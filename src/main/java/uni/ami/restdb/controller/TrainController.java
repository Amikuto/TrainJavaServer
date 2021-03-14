package uni.ami.restdb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uni.ami.restdb.exceptions.ResourceNotFoundException;
import uni.ami.restdb.model.Train;
import uni.ami.restdb.repository.StationRepository;
import uni.ami.restdb.repository.TrainRepository;

import javax.validation.Valid;
import java.util.List;

@RestController
public class TrainController {

    @Autowired
    private TrainRepository trainRepository;

    @Autowired
    private StationRepository stationRepository;

    @GetMapping("/stations/{stationId}/trains")
    public List<Train> getTrainsByQuestionId(@PathVariable Long stationId) {
        return trainRepository.findByArrStationId(stationId);
    }

    @PostMapping("/stations/{stationId}/trains")
    public Train addTrain(@PathVariable Long stationId, @Valid @RequestBody Train train) {
        return stationRepository.findById(stationId)
                .map(station -> {
                    train.setStation(station);
//                    train.setStation_dep(station);
                    return trainRepository.save(train);
                }).orElseThrow(() -> new ResourceNotFoundException("Station not found with id " + stationId));
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
//                    train.setDate_arr(trainRequest.getDate_arr());
//                    train.setDate_dep(trainRequest.getDate_dep());
//                    train.setTime_arr(trainRequest.getTime_arr());
//                    train.setTime_dep(trainRequest.getTime_dep());
                    trainRepository.delete(train);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Train not found with id " + trainId));
    }
}
