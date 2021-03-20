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
import uni.ami.restdb.repository.StationRepository;
import uni.ami.restdb.service.StationService;

import java.util.List;

@Slf4j
@Service
public class StationServiceImpl implements StationService {
    /**
     *
     * @param station
     */

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
    public Page<Station> getAll(Pageable var1) {
        return stationRepository.findAll(var1);
    }

    @Override
    public List<Station> getAllByDepartingTrains(Long id) {
        return stationRepository.findStationByDepTrainId(id);
    }

    @Override
    public List<Station> getAllByArrivingTrains(Long id) {
        return stationRepository.findStationByArrTrainId(id);
    }

    @Override
    public List<Station> getAllByDepartingAndArrivingTrains(Long depTrainId, Long arrTrainId) {
        return stationRepository.findAllByArrTrainIdAndDepTrainId(arrTrainId, depTrainId);
    }
}
