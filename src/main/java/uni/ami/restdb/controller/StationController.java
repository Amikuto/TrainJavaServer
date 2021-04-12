package uni.ami.restdb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uni.ami.restdb.ServiseImpl.StationServiceImpl;
import uni.ami.restdb.model.City;
import uni.ami.restdb.model.Station;
import uni.ami.restdb.repository.StationRepository;

import javax.validation.Valid;
import java.util.List;

@RestController
public class StationController {

//    @Autowired
//    private StationRepository stationRepository;

    @Autowired
    private StationServiceImpl stationService;

    @GetMapping("/stations")
    public Page<Station> getAllStations(Pageable pageable){
        return stationService.getAll(pageable);
    }

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
//        System.out.println(departingStationId);
//        Station station = stationRepository.findById(departingStationId).get();
//        System.out.println(station);
        return stationService.getAllByDepartingTrains(departingStationId);
    }

    @GetMapping("/stations/0/{arrivingStationId}")
    public List<Station> getStationsByArrivingTrains(@PathVariable Long arrivingStationId){
        return stationService.getAllByArrivingTrains(arrivingStationId);
    }

    @GetMapping("/stations/{cityName}/city")
    public List<Station> getStationsByCity(@Valid @PathVariable String cityName) {
        return stationService.getStationsByCityName(cityName);
    }

    @PostMapping("/stations/{cityName}")
    public Station addStation(@Valid @PathVariable String cityName,
                              @Valid @RequestBody Station station) {
        return stationService.save(station, cityName);
    }

    @PutMapping("/stations/{stationId}")
    public Station updateStation(@PathVariable Long stationId,
                                 @Valid @RequestBody Station station) {
        return stationService.update(stationId, station);
    }

    @DeleteMapping("/stations/{stationId}")
    public ResponseEntity<?> deleteStation(@Valid @PathVariable Long stationId) {
        return stationService.delete(stationId);
    }
}
