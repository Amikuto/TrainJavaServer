package uni.ami.restdb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uni.ami.restdb.model.Station;

import java.util.List;

@Repository
public interface StationRepository extends JpaRepository<Station, Long> {
//    List<Station> findByTrainId(Long trainId);
}
