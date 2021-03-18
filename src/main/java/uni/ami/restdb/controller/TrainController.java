package uni.ami.restdb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uni.ami.restdb.exceptions.ResourceNotFoundException;
import uni.ami.restdb.model.Station;
import uni.ami.restdb.model.Train;
import uni.ami.restdb.repository.StationRepository;
import uni.ami.restdb.repository.TrainRepository;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class TrainController {

    @Autowired
    private TrainRepository trainRepository;

    @Autowired
    private StationRepository stationRepository;

    @GetMapping("/stations/{arrStationId}/{depStationId}/trains")
    public List<Train> getTrainsByArrStationAndDepStation(@PathVariable Long arrStationId,
                                                          @PathVariable Long depStationId) {
        return trainRepository.findAllByArrStationIdAndDepStationId(arrStationId, depStationId);
    }

    @GetMapping("/stations/{arrStationId}/trains")
    public List<Train> getTrainsByArrStation(@PathVariable Long arrStationId) {
        return trainRepository.findAllByArrStationId(arrStationId);
    }

    @GetMapping("/stations/0/{depStationId}/trains")
    public List<Train> getTrainsByDepStation(@PathVariable Long depStationId) {
        return trainRepository.findAllByDepStationId(depStationId);
    }

    @PostMapping("/stations/{arrStationId}/{depStationId}/trains")
    public Train addTrain(@PathVariable Long arrStationId,
                          @PathVariable Long depStationId,
                          @Valid @RequestBody Train train) {
        Station arrStation = stationRepository.findById(arrStationId).orElseThrow(() -> new ResourceNotFoundException("Arrival station not found with id " + arrStationId));
        Station depStation = stationRepository.findById(depStationId).orElseThrow(() -> new ResourceNotFoundException("Department station not found with id " + depStationId));

        train.setArrStation(arrStation);
        train.setDepStation(depStation);

        return trainRepository.save(train);
    }


    @PutMapping("/stations/{stationId}/trains/{trainId}")
    public Train updateTrain(@PathVariable Long stationId,
                             @PathVariable Long trainId,
                             @Valid @RequestBody Train trainRequest) {
        if (!stationRepository.existsById(stationId)) {
            throw new ResourceNotFoundException("Station not found with id " + stationId);
        }

        return trainRepository.findById(trainId)
                .map(train -> {
                    train.setDate_arr(trainRequest.getDate_arr());
                    train.setDate_dep(trainRequest.getDate_dep());
                    train.setTime_arr(trainRequest.getTime_arr());
                    train.setTime_dep(trainRequest.getTime_dep());
                    return trainRepository.save(train);
                }).orElseThrow(() -> new ResourceNotFoundException("Train not found with id " + trainId));
    }

    @DeleteMapping("/stations/{stationId}/trains/{trainId}")
    public ResponseEntity<?> deleteTrain(@PathVariable Long stationId,
                                         @PathVariable Long trainId) {
        if (!stationRepository.existsById(stationId)) {
            throw new ResourceNotFoundException("Station not found with id " + stationId);
        }

        return trainRepository.findById(trainId)
                .map(train -> {
                    trainRepository.delete(train);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Train not found with id " + trainId));
    }
}
