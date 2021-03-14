package uni.ami.restdb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uni.ami.restdb.model.Car;
import uni.ami.restdb.model.Train;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {
    List<Car> findByTrainId(Long station_arr_id);
}
