package uni.ami.restdb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uni.ami.restdb.model.Car;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {
    List<Car> findAllByTrainId(Long trainId);
    List<Car> findAllByCarClassId(Long carClass_id);
    List<Car> findAllByCarTypeId(Long carType_id);
}
