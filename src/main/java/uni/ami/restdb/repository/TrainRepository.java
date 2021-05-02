package uni.ami.restdb.repository;

import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uni.ami.restdb.model.Train;

import java.time.LocalDate;
import java.util.Collection;
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
    List<Train> findAllByDepStationNameInAndArrStationNameInAndDateDepEquals(Collection<String> depStation_name, Collection<String> arrStation_name, LocalDate dateDep);

    @Query(value = "SELECT * FROM Train WHERE TO_CHAR(date_dep, 'YYYY-mm-dd') LIKE :year%", nativeQuery = true)
    List<Train> findAllByDateDepIsLike(@Param("year") String date);

    @Query(value = "SELECT count(s) FROM seat s JOIN car ca ON s.car_id = ca.id WHERE ca.train_id = :trainId AND s.ticket_id IS NULL", nativeQuery = true)
    Integer valueOfNotSoldTicketsByTrainId(@Param("trainId") Long trainId);

    @Query(value = "SELECT count(s) FROM seat s JOIN car ca ON s.car_id = ca.id WHERE ca.train_id = :trainId AND s.ticket_id IS NOT NULL", nativeQuery = true)
    Integer valueOfSoldTicketsByTrainId(@Param("trainId") Long trainId);

    @Query(value = "SELECT count(s) FROM seat s JOIN car ca ON s.car_id = ca.id WHERE ca.train_id = :trainId", nativeQuery = true)
    Integer valueOfAllTicketsByTrainId(@Param("trainId") Long trainId);

    @Query(value = "SELECT COUNT(*) FROM Train WHERE TO_CHAR(date_dep, 'YYYY-mm-dd') LIKE :year%", nativeQuery = true)
    Integer findCountOfTrainsByDateDepIsLike(@Param("year") String date);
}
