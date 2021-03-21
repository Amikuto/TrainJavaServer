package uni.ami.restdb.service;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import uni.ami.restdb.model.Car;
import uni.ami.restdb.model.Train;

import java.util.List;

public interface CarService {

    Car save(Car car);

    ResponseEntity<?> delete(Long id);

    Car update(Long id, Car car);

    Car getCarById(Long id);

    Page<Car> getAll(Pageable pageable);

    List<Car> findAllByTrainId(Long id);

    List<Car> findAllByClassId(Long id);

    List<Car> findAllByTypeId(Long id);

    List<Car> findAllByTrainAndClassAndTypeIds(Long trainId, Long carClassId, Long carTypeId);

    List<Car> findAllByTrainAndTypeIds(Long trainId, Long carTypeId);

    List<Car> findAllByTrainAndClassIds(Long trainId, Long carClassId);
}
