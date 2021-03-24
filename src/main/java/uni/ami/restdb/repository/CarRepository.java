package uni.ami.restdb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uni.ami.restdb.model.Car;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    List<Car> findAllByTrainIdEquals(Long trainId);
    List<Car> findAllByCarClassIdEquals(Long carClass_id);
    List<Car> findAllByCarTypeIdEquals(Long carType_id);

    List<Car> findAllByTrainIdAndCarClassIdAndCarTypeIdEquals(Long train_id, Long carClass_id, Long carType_id);
    List<Car> findAllByTrainIdAndCarClassIdEquals(Long train_id, Long carClass_id);
    List<Car> findAllByTrainIdAndCarTypeIdEquals(Long train_id, Long carType_id);
}
