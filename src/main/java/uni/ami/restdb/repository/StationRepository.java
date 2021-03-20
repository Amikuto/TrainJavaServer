package uni.ami.restdb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uni.ami.restdb.model.Station;
import uni.ami.restdb.model.Train;

import java.util.List;

@Repository
public interface StationRepository extends JpaRepository<Station, Long> {
    List<Station> findAllByArrTrainId(Long trainId);
    List<Station> findAllByDepTrainId(Long trainId);
//    List<Station> findAllByDepTrainContains(List<Train> depTrain);
    List<Station> findAllByArrTrainIdAndDepTrainId(Long arrTrain_id, Long depTrain_id);

//    @Query
}
