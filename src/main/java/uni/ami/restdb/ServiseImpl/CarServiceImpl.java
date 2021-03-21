package uni.ami.restdb.ServiseImpl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uni.ami.restdb.exceptions.FindException;
import uni.ami.restdb.model.Car;
import uni.ami.restdb.model.CarClass;
import uni.ami.restdb.model.CarType;
import uni.ami.restdb.model.Train;
import uni.ami.restdb.repository.CarClassRepository;
import uni.ami.restdb.repository.CarRepository;
import uni.ami.restdb.repository.CarTypeRepository;
import uni.ami.restdb.repository.TrainRepository;
import uni.ami.restdb.service.CarService;

import java.util.List;

@Slf4j //TODO: lombok
@Service
public class CarServiceImpl implements CarService {

    @Autowired
    CarRepository carRepository;

    @Autowired
    TrainRepository trainRepository;

    @Autowired
    CarTypeRepository carTypeRepository;

    @Autowired
    CarClassRepository carClassRepository;

    //TODO: переделать с новыми методами????
    @Override
    public Car save(Car car) {
        Train train = trainRepository.findById(car.getTId()).orElseThrow(FindException::new);
        CarClass carClass = carClassRepository.findById(car.getCClass()).orElseThrow(FindException::new);
        CarType carType = carTypeRepository.findById(car.getCType()).orElseThrow(FindException::new);
        car.setTrain(train);
        car.setCarClass(carClass);
        car.setCarType(carType);
        return carRepository.save(car);
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        Car car = getCarById(id);
        carRepository.delete(car);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // TODO: доделать
    @Override
    public Car update(Long id, Car car) {
//        return carRepository.findById(id)
//                .map(car_temp -> {
//                    car_temp.se
//                });
        return null;
    }

    @Override
    public Car getCarById(Long id) {
        return carRepository.findById(id).orElseThrow(FindException::new);
    }

    @Override
    public Page<Car> getAll(Pageable pageable) {
        return carRepository.findAll(pageable);
    }

    @Override
    public List<Car> findAllByTrainId(Long id) {
        return carRepository.findAllByTrainIdEquals(id);
    }

    @Override
    public List<Car> findAllByClassId(Long id) {
        return carRepository.findAllByCarClassIdEquals(id);
    }

    @Override
    public List<Car> findAllByTypeId(Long id) {
        return carRepository.findAllByCarTypeIdEquals(id);
    }

    @Override
    public List<Car> findAllByTrainAndClassAndTypeIds(Long train_id, Long carClass_id, Long carType_id) {
        return carRepository.findAllByTrainIdAndCarClassIdAndCarTypeIdEquals(train_id, carClass_id, carType_id);
    }

    @Override
    public List<Car> findAllByTrainAndTypeIds(Long trainId, Long carTypeId) {
        return carRepository.findAllByTrainIdAndCarTypeIdEquals(trainId, carTypeId);
    }

    @Override
    public List<Car> findAllByTrainAndClassIds(Long trainId, Long carClassId) {
        return carRepository.findAllByTrainIdAndCarClassIdEquals(trainId, carClassId);
    }
}
