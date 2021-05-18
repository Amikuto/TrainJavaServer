package uni.ami.restdb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uni.ami.restdb.serviseImpl.StationServiceImpl;
import uni.ami.restdb.model.Station;

import javax.validation.Valid;
import java.util.List;

/**
 * Контроллер станций
 * @author damir
 */
@RestController
public class StationController {

    @Autowired
    private StationServiceImpl stationService;

    /**
     * Контроллер GET запроса по ссылке "/stations"
     * для получения информации о всех станциях
     * @param pageable параметр заглушка
     * @return возвращает страницу со всеми станциями из базы данных
     */
    @GetMapping("/stations")
    public Page<Station> getAllStations(Pageable pageable){
        return stationService.getAll(pageable);
    }

    /**
     * Контроллер GET запроса по ссылке "/stations/{stationId}"
     * для получения информации о станции по ее id
     * @param stationId параметр id станции
     * @return возвращает класс станции
     */
    @GetMapping("/stations/{stationId}")
    public Station getStationById(@PathVariable Long stationId){
        return stationService.getStationById(stationId);
    }

    @GetMapping("/stations/{departingStationId}/{arrivingStationId}")
    public List<Station> getStationsByDepartingAndArrivingTrains(@PathVariable Long arrivingStationId,
                                                                 @PathVariable Long departingStationId){
        return stationService.getAllByDepartingAndArrivingTrains(departingStationId, arrivingStationId);
    }

    @GetMapping("/stations/{departingStationId}/0")
    public List<Station> getStationsByDepartingTrains(@PathVariable Long departingStationId){
        return stationService.getAllByDepartingTrains(departingStationId);
    }

    @GetMapping("/stations/0/{arrivingStationId}")
    public List<Station> getStationsByArrivingTrains(@PathVariable Long arrivingStationId){
        return stationService.getAllByArrivingTrains(arrivingStationId);
    }

    /**
     * Контроллер GET запроса по ссылке "/stations/{cityName}/city"
     * для получения информации о станцих, находящихся в заданном городе
     * @param cityName параметр имени города
     * @return возвращает список станций
     */
    @GetMapping("/stations/{cityName}/city")
    public List<Station> getStationsByCity(@Valid @PathVariable String cityName) {
        return stationService.getStationsByCityName(cityName);
    }

    /**
     * Контроллер POST запроса по ссылке "/stations/{cityName}"
     * для добавления новой станции в базу данных
     * @param cityName параметр имени города, в котором добавить станцию
     * @param station JSON данные о станции
     * @return возвращает сохраненный класс станции
     */
    @PostMapping("/stations/{cityName}")
    public Station addStation(@Valid @PathVariable String cityName,
                              @Valid @RequestBody Station station) {
        return stationService.save(station, cityName);
    }

    /**
     * Контроллер PUT запроса по ссылке "/stations/{stationId}"
     * для изменения информации о существующей станции
     * @param stationId параметр id станции
     * @param station JSON новые данные о станции
     * @return возвращает измененный класс станции
     */
    @PutMapping("/stations/{stationId}")
    public Station updateStation(@PathVariable Long stationId,
                                 @Valid @RequestBody Station station) {
        return stationService.update(stationId, station);
    }

    /**
     * Контроллер DELETE запроса по ссылке "/stations/{stationId}"
     * для удаления станции из базы данных
     * @param stationId параметр id станции
     * @return возвращает код статуса
     */
    @DeleteMapping("/stations/{stationId}")
    public ResponseEntity<?> deleteStation(@Valid @PathVariable Long stationId) {
        return stationService.delete(stationId);
    }
}
