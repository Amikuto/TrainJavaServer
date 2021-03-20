package uni.ami.restdb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uni.ami.restdb.ServiseImpl.StationServiceImpl;
import uni.ami.restdb.model.Station;
import uni.ami.restdb.repository.StationRepository;

import javax.validation.Valid;
import java.util.List;

@RestController
public class StationController {

    @Autowired
    private StationServiceImpl stationService;

    @GetMapping("/stations")
    public Page<Station> getStation(Pageable pageable){
        return stationService.getAll(pageable);
    }

    @GetMapping("/stations/{trainArrStationId}/{trainDepStationId}")
    public List<Station> getStationByArr(@PathVariable Long trainArrStationId,
                                         @PathVariable Long trainDepStationId){
        return stationService.getAllByDepartingAndArrivingTrains(trainDepStationId, trainArrStationId);
    }

    @GetMapping("/stations/{trainArrStationId}")
    public List<Station> getStationByArr(@PathVariable Long trainArrStationId){
        return stationService.getAllByArrivingTrains(trainArrStationId);
    }

    @PostMapping("/stations")
    public Station createStation(@Valid @RequestBody Station station) {
        return stationService.save(station);
    }

    @PutMapping("/stations/{stationId}")
    public Station updateStation(@PathVariable Long stationId, @Valid @RequestBody Station station) {
        return stationService.update(stationId, station);
    }

    @DeleteMapping("/stations/{stationId}")
    public ResponseEntity<?> deleteStation(@PathVariable Long stationId) {
        return stationService.delete(stationId);
    }
}
