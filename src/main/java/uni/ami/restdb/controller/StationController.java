package uni.ami.restdb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uni.ami.restdb.exceptions.ResourceNotFoundException;
import uni.ami.restdb.model.Station;
import uni.ami.restdb.repository.StationRepository;

import javax.validation.Valid;
import java.util.List;

@RestController
public class StationController {

    @Autowired
    private StationRepository stationRepository;

    @GetMapping("/stations")
    public Page<Station> getStation(Pageable pageable){
        return stationRepository.findAll(pageable);
    }

    @GetMapping("/stations/{trainArrStationId}/{trainDepStationId}")
    public List<Station> getStationByArr(@PathVariable Long trainArrStationId,
                                         @PathVariable Long trainDepStationId){
        return stationRepository.findAllByArrTrainIdAndDepTrainId(trainDepStationId, trainArrStationId);
    }

    @GetMapping("/stations/{trainArrStationId}")
    public List<Station> getStationByArr(@PathVariable Long trainArrStationId){
        return stationRepository.findStationByArrTrainId(trainArrStationId);
    }

    @PostMapping("/stations")
    public Station createStation(@Valid @RequestBody Station station) {
        return stationRepository.save(station);
    }

    @PutMapping("/stations/{stationId}")
    public Station updateStation(@PathVariable Long stationId, @Valid @RequestBody Station stationRequest) {
        return stationRepository.findById(stationId)
                .map(station -> {
                    station.setCity(stationRequest.getCity());
                    station.setName(stationRequest.getName());
                    return stationRepository.save(station);
                }).orElseThrow(() -> new ResourceNotFoundException("Station not found with id " + stationId));
    }

    @DeleteMapping("/stations/{stationId}")
    public ResponseEntity<?> deleteStation(@PathVariable Long stationId) {
        return stationRepository.findById(stationId)
            .map(station -> {
                stationRepository.delete(station);
                return ResponseEntity.ok().build();
            }).orElseThrow(() -> new ResourceNotFoundException("Station not found with id " + stationId));
    }
}
