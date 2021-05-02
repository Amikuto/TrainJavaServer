package uni.ami.restdb.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uni.ami.restdb.serviseImpl.TrainServiceImpl;
import uni.ami.restdb.model.Train;

import javax.validation.Valid;
import java.util.List;

@RestController
public class TrainController {

    @Autowired
    private TrainServiceImpl trainService;

    @GetMapping("/trains")
    public Page<Train> getAllTrains(Pageable pageable) {
        return trainService.getAll(pageable);
    }

    @GetMapping("/trains/{trainId}")
    public Train getTrainById(@PathVariable Long trainId) {
        return trainService.getTrainById(trainId);
    }

    @GetMapping("/trains/{depStationId}/{arrStationId}/stations")
    public List<Train> getTrainsByDepartingAndArrivingStation(@PathVariable Long arrStationId,
                                                              @PathVariable Long depStationId) {
        return trainService.findAllByDepartingStationAndArrivingStation(depStationId, arrStationId);
    }

    @GetMapping("/trains/0/{arrStationId}/stations/")
    public List<Train> getTrainsByArrStation(@PathVariable Long arrStationId) {
        return trainService.findAllByArrStationId(arrStationId);
    }

    @GetMapping("/trains/{depStationId}/0/stations")
    public List<Train> getTrainsByDepStation(@PathVariable Long depStationId) {
        return trainService.findAllByDepStationId(depStationId);
    }

    @GetMapping("/trains/{depStationName}/{arrStationName}/{depDate}")
    public List<Train> getTrainsByDepartingAndArrivingStation(@PathVariable String depStationName,
                                                              @PathVariable String arrStationName,
                                                              @PathVariable String depDate) {
        return trainService.findAllByArrivingStationAndDepartingStationAndDate(depStationName, arrStationName, depDate);
    }

    @GetMapping("/trains/data/year-statistic/{date}")
    public List<Train> findAllByYearDep(@PathVariable String date) {
        return trainService.findAllByYearDep(date);
    }

    @GetMapping("/trains/data/train-tickets-data/{trainId}")
    public String valueTicketsDataByTrainId(@PathVariable Long trainId) throws JsonProcessingException {
        return trainService.valueTicketsDataByTrainId(trainId);
    }

    @PostMapping("/trains")
    public Train addTrain(@Valid @RequestBody Train train) {
        return trainService.save(train);
    }


    @PutMapping("/trains/{trainId}")
    public Train updateTrain(@PathVariable Long trainId,
                             @Valid @RequestBody Train train) {
        return trainService.update(trainId, train);
    }

    @DeleteMapping("/trains/{trainId}")
    public ResponseEntity<?> deleteTrain(@PathVariable Long trainId) {
        return trainService.delete(trainId);
    }
}
