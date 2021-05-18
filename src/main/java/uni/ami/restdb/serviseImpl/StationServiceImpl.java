package uni.ami.restdb.serviseImpl;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.PropertyValueException;
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
import uni.ami.restdb.repository.CityRepository;
import uni.ami.restdb.repository.StationRepository;
import uni.ami.restdb.service.StationService;

import java.util.List;

/**
 * Класс сервиса станций
 * @author damir
 */
@Slf4j
@Service
public class StationServiceImpl implements StationService {

    @Autowired
    StationRepository stationRepository;

    @Autowired
    CityRepository cityRepository;

    /**
     * Функция сохранения станции в базе данных
     * @param station параметр данных станций {@link Station}
     * @param cityName параметр названия города {@link City}
     * @return возвразает сохраненную станцию {@link Station}
     */
    @Override
    public Station save(Station station, String cityName) {
            City city = cityRepository.findByNameEquals(cityName);
            if (city != null) {
                station.setCity(city);
                station.setCityName(city.getName());
                try {
                    return stationRepository.save(station);
                } catch (Exception e) {
                    return null;
                }
            } else {
                throw new ResourceNotFoundException("Города с таким названием не найдено!");
            }
    }

    /**
     * Функция удаления станции из базы данных
     * @param id принимет в качестве параметра id станции
     * @return возвращает HttpStatus.OK {@link ResponseEntity}
     */
    @Override
    public ResponseEntity<?> delete(Long id) {
        Station station = stationRepository.findById(id).orElseThrow(FindException::new);
        stationRepository.delete(station);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Функция изменения информации о станции в базе данных
     * @param id принимет в качестве параметра id станции
     * @param station принимет в качестве параметра класс станции для изменения данных {@link Station}
     * @return возвращает класс станции с измененной информацией {@link Station}
     */
    @Override
    public Station update(Long id, Station station) {
        return stationRepository.findById(id)
                .map(station_temp -> {
                    if (station.getCityName() != null){
                            City city = cityRepository.findByNameEquals(station.getCityName());
                            station_temp.setCity(city);
                            station_temp.setCityName(city.getName());
                    }
                    if (station.getName() != null) {
                        station_temp.setName(station.getName());
                    } else {
                        throw new ResourceNotFoundException("Название станции не задано!");
                    }
                    return stationRepository.save(station_temp);
                }).orElseThrow(() -> new ResourceNotFoundException("Станции с данным id не найдено!"));
    }

    /**
     * Функция поиска станции по id
     * @param id принимет в качестве параметра id станции
     * @return возвращает класс станции {@link Station}
     */
    @Override
    public Station getStationById(Long id) {
        return stationRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Станции с данным id не найдено!"));
    }

    /**
     * Функция поиска всех станций в базе данных
     * @param pageable
     * @return возвращает список всех станций в формате Pageable {@link Pageable}
     */
    @Override
    public Page<Station> getAll(Pageable pageable) {
        return stationRepository.findAll(pageable);
    }

    @Override
    public List<Station> getAllByDepartingTrains(Long id) {
        return stationRepository.findAllByDepTrainIdEquals(id);
    }

    @Override
    public List<Station> getAllByArrivingTrains(Long id) {
        return stationRepository.findAllByArrTrainIdEquals(id);
    }

    @Override
    public List<Station> getAllByDepartingAndArrivingTrains(Long departingStationId, Long arrivingStationId) {
        return stationRepository.findAllByDepTrainIdAndArrTrainIdEquals(departingStationId, arrivingStationId);
    }

    public Long getStationIdByName(String name){
        return stationRepository.findStationIdByNameEquals(name);
    }

    /**
     * Функция поиска станций по названию города в котором они распаложены
     * @param cityName параметр названия города {@link City}
     * @return возвращает список всех станций
     */
    public List<Station> getStationsByCityName(String cityName) {
        return stationRepository.findAllByCity_NameEquals(cityName);
    }
}
