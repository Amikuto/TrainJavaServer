package uni.ami.restdb.serviseImpl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uni.ami.restdb.exceptions.FindException;
import uni.ami.restdb.exceptions.ResourceNotFoundException;
import uni.ami.restdb.model.City;
import uni.ami.restdb.model.Station;
import uni.ami.restdb.model.Train;
import uni.ami.restdb.repository.CityRepository;
import uni.ami.restdb.repository.StationRepository;
import uni.ami.restdb.repository.TrainRepository;
import uni.ami.restdb.service.TrainService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Класс сервиса поездов
 * @author damir
 */
@Slf4j
@Service
public class TrainServiceImpl implements TrainService {

    @Autowired
    CityRepository cityRepository;

    @Autowired
    TrainRepository trainRepository;

    @Autowired
    StationRepository stationRepository;

    /**
     * Функция сохранения поезда в базе данных
     * @param train принимает класс поезда для сохранения {@link Train}
     * @return возвразает сохраненный поезд {@link Train}
     */
    @Override
    public Train save(Train train) {
        try {
            Station stationDeparting = stationRepository.findStationByNameEqualsAndCity_NameEquals(train.getDepSt(), train.getDepartingCity());
            Station stationArrival = stationRepository.findStationByNameEqualsAndCity_NameEquals(train.getArrSt(), train.getArrivalCity());

            train.setDepStation(stationDeparting);
            train.setDepSt(stationDeparting.getName());
            train.setDepartingCity(stationDeparting.getCityName());

            train.setArrStation(stationArrival);
            train.setArrSt(stationArrival.getName());
            train.setArrivalCity(stationArrival.getCityName());
        } catch (NullPointerException e) {
            throw new ResourceNotFoundException("Данных станций не найдено!");
        }

        return trainRepository.save(train);
    }

    /**
     * Функция удаления поезда из базы данных
     * @param id принимет в качестве параметра id поезда
     * @return возвращает HttpStatus.OK {@link ResponseEntity}
     */
    @Override
    public ResponseEntity<?> delete(Long id) {
        Train train = trainRepository.findById(id).orElseThrow(FindException::new);
        trainRepository.delete(train);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Функция изменения информации о поезде в базе данных
     * @param id принимет в качестве параметра id поезда
     * @param train принимет в качестве параметра класс поезда для изменения данных {@link Train}
     * @return возвращает класс поезда с измененной информацией {@link Train}
     */
    @Override
    public Train update(Long id, Train train) {
        return trainRepository.findById(id)
                .map(train_temp -> {
                    train_temp.setDateDep(train.getDateDep());
                    train_temp.setDateArr(train.getDateArr());
                    train_temp.setTimeDep(train.getTimeDep());
                    train_temp.setTimeArr(train.getTimeArr());

                    try {
                        Station stationDeparting = stationRepository.findStationByNameEqualsAndCity_NameEquals(train.getDepSt(), train.getDepartingCity());
                        Station stationArrival = stationRepository.findStationByNameEqualsAndCity_NameEquals(train.getArrSt(), train.getArrivalCity());

                        train.setDepStation(stationDeparting);
                        train.setDepSt(stationDeparting.getName());
                        train.setDepartingCity(stationDeparting.getCityName());

                        train.setArrStation(stationArrival);
                        train.setArrSt(stationArrival.getName());
                        train.setArrivalCity(stationArrival.getCityName());
                    } catch (NullPointerException e) {
                        throw new ResourceNotFoundException("Данных станций не найдено!");
                    }

                    return trainRepository.save(train_temp);
                }).orElseThrow(() -> new ResourceNotFoundException("Поезда с заданным id не найдено!"));
    }

    /**
     * Функция поиска поезда по id
     * @param id принимет в качестве параметра id поезда
     * @return возвращает класс поезда {@link Train}
     */
    @Override
    public Train getTrainById(Long id) {
        return trainRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Поезда с заданным id не найдено!"));
    }

    /**
     * Функция поиска всех поездов в базе данных
     * @param pageable
     * @return возвращает список всех поездов в формате Pageable {@link Pageable}
     */
    @Override
    public Page<Train> getAll(Pageable pageable) {
        return trainRepository.findAll(pageable);
    }

    /**
     * Функция поиска поездов по станции отпправления
     * @param id параметр id станции отправления
     * @return возвращает список поездов
     */
    @Override
    public List<Train> findAllByDepStationId(Long id) {
        return trainRepository.findAllByDepStationIdEquals(id);
    }

    /**
     * Функция поиска поездов по станции прибытия
     * @param id параметр id станции прибытия
     * @return возвращает список поездов
     */
    @Override
    public List<Train> findAllByArrStationId(Long id) {
        return trainRepository.findAllByArrStationIdEquals(id);
    }

    /**
     * Функция поиска поездов по станции отпправления и прибытия
     * @param depStationId параметр id станции отправления
     * @param arrStationId параметр id станции прибытия
     * @return возвращает список поездов
     */
    @Override
    public List<Train> findAllByDepartingStationAndArrivingStation(Long depStationId, Long arrStationId) {
        return trainRepository.findAllByDepStationIdAndArrStationIdEquals(depStationId, arrStationId);
    }

    /**
     * Функция нахождения всех поездов по городу отправления и прибытия, а так же под дате отправления
     * @param depCityName параметр города отправления
     * @param arrCityName параметр города прибытия
     * @param date параметр даты отправления
     * @return возвращает список поездов
     */
    @Override
    public List<Train> findAllByArrivingStationAndDepartingStationAndDate(String depCityName, String arrCityName, String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate depDate = LocalDate.parse(date, formatter);

        try {
            City depCity = cityRepository.findByNameEquals(depCityName);
            City arrCity = cityRepository.findByNameEquals(arrCityName);

            List<String> stationDepList = stationRepository.findAllByCityIdEquals(depCity.getId()).stream().map(Station::getName).collect(Collectors.toList());
            List<String> stationArrList = stationRepository.findAllByCityIdEquals(arrCity.getId()).stream().map(Station::getName).collect(Collectors.toList());
            return trainRepository.findAllByDepStationNameInAndArrStationNameInAndDateDepEquals(stationDepList, stationArrList, depDate);
        } catch (NullPointerException e) {
            throw new ResourceNotFoundException("Данных городов не найдено!");
        }
    }

    /**
     * Функция нахождения всех поездов по году начала их маршрута
     * @param date параметр даты отправления
     * @return возвращает список всех поездов
     */
    public List<Train> findAllByYearDep(String date) {
        return trainRepository.findAllByDateDepIsLike(date);
    }

    /**
     * Функция нахождения количества проданных билетов в поезде
     * @param trainId параметр id поезда
     * @return возвращает число билетов
     */
    public Integer valueOfSoldTicketsDataByTrainId(Long trainId) {
        return trainRepository.valueOfSoldTicketsByTrainId(trainId);
    }

    /**
     * Функция нахождения количества не проданных билетов в поезде
     * @param trainId параметр id поезда
     * @return возвращает число билетов
     */
    public Integer valueOfNotSoldTicketsDataByTrainId(Long trainId) {
        return trainRepository.valueOfNotSoldTicketsByTrainId(trainId);
    }

    /**
     * Функция нахождения количества всех билетов в поезде
     * @param trainId параметр id поезда
     * @return возвращает число билетов
     */
    public Integer valueOfAllTicketsDataByTrainId(Long trainId) {
        return trainRepository.valueOfAllTicketsByTrainId(trainId);
    }

    /**
     * Функция нахождения количества поездов по их году маршрута
     * @param year параметр года начала маршрута
     * @return число поездов
     */
    public Integer findCountOfTrainsByDateDepIsLike(String year) {
        return trainRepository.findCountOfTrainsByDateDepIsLike(year);
    }
}
