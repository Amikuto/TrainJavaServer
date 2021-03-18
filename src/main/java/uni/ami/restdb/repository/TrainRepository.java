package uni.ami.restdb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uni.ami.restdb.model.Train;

import java.util.List;

@Repository
public interface TrainRepository extends JpaRepository<Train, Long> {
    List<Train> findAllByArrStationIdAndDepStationId(Long arrStation, Long depStation);
    List<Train> findAllByArrStationId(Long arrStation);
    List<Train> findAllByDepStationId(Long arrStation);
}
