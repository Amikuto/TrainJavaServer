package uni.ami.restdb.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import uni.ami.restdb.model.Station;

import java.util.List;

public interface StationService {

    Station save(Station station, String cityName);

    ResponseEntity<?> delete(Long id);

    Station update(Long id, Station station);

    Station getStationById(Long id);

    Page<Station> getAll(Pageable pageable);

    List<Station> getAllByDepartingTrains(Long id);

    List<Station> getAllByArrivingTrains(Long id);

    List<Station> getAllByDepartingAndArrivingTrains(Long depTrainId, Long arrTrainId);
}
