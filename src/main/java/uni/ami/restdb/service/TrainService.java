package uni.ami.restdb.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import uni.ami.restdb.model.Station;
import uni.ami.restdb.model.Train;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface TrainService {

    Train save(Train train);

    ResponseEntity<?> delete(Long id);

    Train update(Long id, Train train);

    Train getTrainById(Long id);

    Page<Train> getAll(Pageable pageable);

    List<Train> findAllByDepStationId(Long id);

    List<Train> findAllByArrStationId(Long id);

    List<Train> findAllByDepartingStationAndArrivingStation(Long depStationId, Long arrStationId);

    List<Train> findAllByArrivingStationAndDepartingStationAndDate(Long depStationId, Long arrStationId, String depDate);

//    List<Train> getAllByDepartingTrains(Long id);
//
//    List<Train> getAllByArrivingTrains(Long id);
//
//    List<Train> getAllByDepartingAndArrivingTrains(Long depTrainId, Long arrTrainId);
}
