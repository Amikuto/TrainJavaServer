package uni.ami.restdb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uni.ami.restdb.model.Train;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Repository
public interface TrainRepository extends JpaRepository<Train, Long> {
    List<Train> findAllByDepStationIdAndArrStationId(Long depStation, Long arrStation);
    List<Train> findAllByArrStationId(Long arrStation);
    List<Train> findAllByDepStationId(Long arrStation);

    List<Train> findAllByDepStationIdAndArrStationIdEquals(Long depStationId, Long arrStationId);
    List<Train> findAllByDepStationIdEquals(Long depStationId);
    List<Train> findAllByArrStationIdEquals(Long arrStationId);
    List<Train> findAllByDepStationIdAndArrStationIdAndDateDepEquals(Long depStationId, Long arrStationId, LocalDate depDate);
}
