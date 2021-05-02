package uni.ami.restdb.serviseImpl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
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

@Slf4j
@Service
public class TrainServiceImpl implements TrainService {

    @Autowired
    CityRepository cityRepository;

    @Autowired
    TrainRepository trainRepository;

    @Autowired
    StationRepository stationRepository;

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

    @Override
    public ResponseEntity<?> delete(Long id) {
        Train train = trainRepository.findById(id).orElseThrow(FindException::new);
        trainRepository.delete(train);
        return new ResponseEntity<>(HttpStatus.OK);
    }

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

    @Override
    public Train getTrainById(Long id) {
        return trainRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Поезда с заданным id не найдено!"));
    }

    @Override
    public Page<Train> getAll(Pageable pageable) {
        return trainRepository.findAll(pageable);
    }

    @Override
    public List<Train> findAllByDepStationId(Long id) {
        return trainRepository.findAllByDepStationIdEquals(id);
    }

    @Override
    public List<Train> findAllByArrStationId(Long id) {
        return trainRepository.findAllByArrStationIdEquals(id);
    }

    @Override
    public List<Train> findAllByDepartingStationAndArrivingStation(Long depStationId, Long arrStationId) {
        return trainRepository.findAllByDepStationIdAndArrStationIdEquals(depStationId, arrStationId);
    }

    @Override
    public List<Train> findAllByArrivingStationAndDepartingStationAndDate(String depStationName, String arrStationName, String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate depDate = LocalDate.parse(date, formatter);

        try {
            City depCity = cityRepository.findByNameEquals(depStationName);
            City arrCity = cityRepository.findByNameEquals(arrStationName);

            List<String> stationDepList = stationRepository.findAllByCityIdEquals(depCity.getId()).stream().map(Station::getName).collect(Collectors.toList());
            List<String> stationArrList = stationRepository.findAllByCityIdEquals(arrCity.getId()).stream().map(Station::getName).collect(Collectors.toList());
            return trainRepository.findAllByDepStationNameInAndArrStationNameInAndDateDepEquals(stationDepList, stationArrList, depDate);
        } catch (NullPointerException e) {
            throw new ResourceNotFoundException("Данных городов не найдено!");
        }
    }

    public List<Train> findAllByYearDep(String date) {
        return trainRepository.findAllByDateDepIsLike(date);
    }

    public String valueTicketsDataByTrainId(Long trainId) throws JsonProcessingException {
        Long soldTickets = trainRepository.valueOfSoldTicketsByTrainId(trainId);
        Long notSoldTickets = trainRepository.valueOfNotSoldTicketsByTrainId(trainId);
        Long allTickets = trainRepository.valueOfAllTicketsByTrainId(trainId);

        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        Map<String, Long> ticketsData = new HashMap<>();
        ticketsData.put("soldTickets", soldTickets);
        ticketsData.put("notSoldTickets", notSoldTickets);
        ticketsData.put("allTickets", allTickets);

        String json = mapper.writeValueAsString(ticketsData);
        return json;
    }
}
