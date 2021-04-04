package uni.ami.restdb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uni.ami.restdb.ServiseImpl.TrainServiceImpl;
import uni.ami.restdb.model.Train;

import javax.validation.Valid;
import java.time.LocalDate;
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

//        System.out.println(trainService.getTrainById(10L));
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

    @GetMapping("/trains/{depStationId}/{arrStationId}/{depDate}")
    public List<Train> getTrainsByDepartingAndArrivingStation(@PathVariable Long arrStationId,
                                                              @PathVariable Long depStationId,
                                                              @PathVariable String depDate) {
        return trainService.findAllByArrivingStationAndDepartingStationAndDate(depStationId, arrStationId, depDate);
    }

//    @PostMapping("/stations/{arrStationId}/{depStationId}/trains")
//    public Train addTrain(@PathVariable Long arrStationId,
//                          @PathVariable Long depStationId,
//                          @Valid @RequestBody Train train) {
//        Station arrStation = stationRepository.findById(arrStationId).orElseThrow(() -> new ResourceNotFoundException("Arrival station not found with id " + arrStationId));
//        Station depStation = stationRepository.findById(depStationId).orElseThrow(() -> new ResourceNotFoundException("Department station not found with id " + depStationId));
//
//        train.setArrStation(arrStation);
//        train.setDepStation(depStation);
//
//        return trainService.save(train);
//    }

    @PostMapping("/trains")
    public Train addTrain(@Valid @RequestBody Train train) {
//        System.out.println(train);
//        System.out.println(train);

//        Station stationArrival = stationRepository.findById(train.getArrSt()).orElseThrow(() -> new ResourceNotFoundException("pass"));
//        Station stationDepartment = stationRepository.findById(train.getDepSt()).orElseThrow(() -> new ResourceNotFoundException("pass"));
//
//        train.setArrStation(stationArrival);
//        train.setDepStation(stationDepartment);
//        System.out.println(train);

        return trainService.save(train);
    }


    @PutMapping("/trains/{trainId}")
    public Train updateTrain(@PathVariable Long trainId,
                             @Valid @RequestBody Train train) {
        return trainService.update(trainId, train);
//        return trainRepository.findById(trainId)
//                .map(train -> {
//                    train.setDate_arr(trainRequest.getDate_arr());
//                    train.setDate_dep(trainRequest.getDate_dep());
//                    train.setTime_arr(trainRequest.getTime_arr());
//                    train.setTime_dep(trainRequest.getTime_dep());
//                    return trainRepository.save(train);
//                }).orElseThrow(() -> new ResourceNotFoundException("Train not found with id " + trainId));
    }

    @DeleteMapping("/trains/{trainId}")
    public ResponseEntity<?> deleteTrain(@PathVariable Long trainId) {
        return trainService.delete(trainId);
    }
}
