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
import uni.ami.restdb.service.StationService;
import uni.ami.restdb.service.TrainService;

import java.util.List;

@Slf4j //TODO: lombok
@Service
public class StationServiceImpl implements StationService {

    @Autowired
    StationRepository stationRepository;

    @Override
    public Station save(Station station) {
        return stationRepository.save(station);
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
//        if (stationRepository.findById(id).isPresent()) {
//            Station station = stationRepository.findById(id).get();
//            stationRepository.delete(station);
//        }// else throw FindException;

        Station station = stationRepository.findById(id).orElseThrow(FindException::new);

        stationRepository.delete(station);

        return new ResponseEntity<>(HttpStatus.OK);

//        return stationRepository.findById(id)
//                .map(station -> {
////                    stationRepository.delete(station);
//                    return stationRepository.delete(station);
//                }).orElseThrow(FindException::new);
    }

    @Override
    public Station update(Long id, Station station) {
//        if (stationRepository.findById(id).isPresent()) {
//            Station station_temp = stationRepository.findById(id).get();
//            station_temp.setName(station.getName());
//            station_temp.setCity(station.getCity());
//            return stationRepository.save(station_temp);
//        }

        return stationRepository.findById(id)
                .map(station_temp -> {
                    station_temp.setCity(station.getCity());
                    station_temp.setName(station.getName());
                    return stationRepository.save(station_temp);
                }).orElseThrow(FindException::new);
    }

    @Override
    public Station getStationById(Long id) {
//        if (stationRepository.findById(id).isPresent()) {
//            return stationRepository.findById(id).get();
//        } else {
//            return null;
//        }

        return stationRepository.findById(id).orElseThrow(FindException::new);
    }

    @Override
    public Page<Station> getAll(Pageable pageable) {
        return stationRepository.findAll(pageable);
    }

    @Override
    public List<Station> getAllByDepartingTrains(Long id) {
        return stationRepository.findAllByDepTrainIdEquals(id);
    }

    @Override
    public List<Station> getAllByArrivingTrains(Long id) {
        return stationRepository.findAllByArrTrainIdEquals(id);
    }

    //TODO: удалить?
    @Override
    public List<Station> getAllByDepartingAndArrivingTrains(Long departingStationId, Long arrivingStationId) {
        return stationRepository.findAllByDepTrainIdAndArrTrainIdEquals(departingStationId, arrivingStationId);
    }

    public Long getStationIdByName(String name){
        return stationRepository.findStationIdByNameEquals(name);
    }
}
