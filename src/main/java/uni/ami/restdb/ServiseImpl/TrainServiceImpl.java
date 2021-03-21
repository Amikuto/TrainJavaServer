package uni.ami.restdb.ServiseImpl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uni.ami.restdb.exceptions.FindException;
import uni.ami.restdb.model.Station;
import uni.ami.restdb.model.Train;
import uni.ami.restdb.repository.StationRepository;
import uni.ami.restdb.repository.TrainRepository;
import uni.ami.restdb.service.TrainService;

import java.util.List;

@Slf4j //TODO: lombok
@Service
public class TrainServiceImpl implements TrainService {

    @Autowired
    TrainRepository trainRepository;

    @Autowired
    StationRepository stationRepository;

    @Override
    public Train save(Train train) {
        Station stationArrival = stationRepository.findById(train.getArrSt()).orElseThrow(FindException::new);
        Station stationDepartment = stationRepository.findById(train.getDepSt()).orElseThrow(FindException::new);

        train.setArrStation(stationArrival);
        train.setDepStation(stationDepartment);

        return trainRepository.save(train);
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        Train train = trainRepository.findById(id).orElseThrow(FindException::new);
        trainRepository.delete(train);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public Train update(Long id, Train train) {
        return trainRepository.findById(id)
                .map(station_temp -> {
                    station_temp.setDate_dep(train.getDate_dep());
                    station_temp.setDate_arr(train.getDate_arr());
                    station_temp.setTime_dep(train.getTime_dep());
                    station_temp.setTime_arr(train.getTime_arr());
                    station_temp.setDepSt(train.getDepSt());
                    station_temp.setArrSt(train.getArrSt());

                    station_temp.setCars(train.getCars());
                    return save(station_temp);
                }).orElseThrow(FindException::new);
    }

    @Override
    public Train getTrainById(Long id) {
        return trainRepository.findById(id).orElseThrow(FindException::new);
    }

    @Override
    public Page<Train> getAll(Pageable pageable) {
        return trainRepository.findAll(pageable);
    }

    @Override
    public List<Train> findAllByDepStationId(Long id) {
        return trainRepository.findAllByDepStationIdEquals(id);
    }

    @Override
    public List<Train> findAllByArrStationId(Long id) {
        return trainRepository.findAllByArrStationIdEquals(id);
    }

    @Override
    public List<Train> findAllByDepartingStationAndArrivingStation(Long depStationId, Long arrStationId) {
        return trainRepository.findAllByDepStationIdAndArrStationIdEquals(depStationId, arrStationId);
    }
}
