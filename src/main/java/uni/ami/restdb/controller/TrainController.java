package uni.ami.restdb.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonProcessingException;
import jdk.jfr.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
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

    @GetMapping("/trains/search/{depStationName}/{arrStationName}/{depDate}")
    public List<Train> getTrainsByDepartingAndArrivingStation(@PathVariable String depStationName,
                                                              @PathVariable String arrStationName,
                                                              @PathVariable String depDate) {
        return trainService.findAllByArrivingStationAndDepartingStationAndDate(depStationName, arrStationName, depDate);
    }

    @GetMapping("/trains/data/year-statistic/{date}")
    public List<Train> findAllByYearDep(@PathVariable String date) {
        return trainService.findAllByYearDep(date);
    }

    @GetMapping("/trains/data/year-statistic-count/{date}")
    public Integer findCountOfTrainsByDateDepIsLike(@PathVariable String date) {
        return trainService.findCountOfTrainsByDateDepIsLike(date);
    }

    @GetMapping(value = "/trains/data/train-sold-tickets-data/{trainId}")
    public Integer valueOfSoldTicketsDataByTrainId(@PathVariable Long trainId) {
        return trainService.valueOfSoldTicketsDataByTrainId(trainId);
    }

    @GetMapping(value = "/trains/data/train-notsold-tickets-data/{trainId}")
    public Integer valueOfNotSoldTicketsDataByTrainId(@PathVariable Long trainId) {
        return trainService.valueOfNotSoldTicketsDataByTrainId(trainId);
    }

    @GetMapping(value = "/trains/data/train-all-tickets-data/{trainId}")
    public Integer valueOfAllTicketsDataByTrainId(@PathVariable Long trainId) {
        return trainService.valueOfAllTicketsDataByTrainId(trainId);
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
