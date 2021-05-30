package uni.ami.restdb.serviseImpl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uni.ami.restdb.exceptions.ResourceNotFoundException;
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

/**
 * Класс сервиса вагонов
 * @author damir
 */
@Slf4j
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

    /**
     * Функция сохранения вагона в базе данных
     * находит в базе данных поезд с переданным id и добавляет его сущность классу вагона
     * @param car принимает класс вагона для сохранения {@link Car}
     * @return возвращает сохраненный вагон {@link Car}
     * @throws ResourceNotFoundException в случае если тип или класс вагона с переданным именем не найдены возвращает ошибку поиска
     */
    @Override
    public Car save(Car car) throws ResourceNotFoundException {
        Train train = trainRepository.findById(car.getTId()).orElseThrow(() -> new ResourceNotFoundException("error"));
        car.setTrain(train);
        car.setTId(train.getId());

        try {
            CarClass carClass = carClassRepository.findByNameEquals(car.getCClass());
            car.setCarClass(carClass);
            car.setCClass(carClass.getName());

            CarType carType = carTypeRepository.findByNameEquals(car.getCType());
            car.setCarType(carType);
            car.setCType(carType.getName());
        } catch (NullPointerException e) {
            throw new ResourceNotFoundException("error");
        }
        return carRepository.save(car);
    }

    /**
     * Функция удаленя вагона из базы данных
     * @param id принимет Long параметр id вагона
     * @return возвращает HttpStatus.OK {@link ResponseEntity}
     */
    @Override
    public ResponseEntity<?> delete(Long id) {
        Car car = getCarById(id);
        carRepository.delete(car);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Функция изменения информации о вагоне в базе данных
     * @param id Long параметр id вагона
     * @param car принимает класс вагона для изменения данных {@link Car}
     * @return возвращает измененный вагон {@link Car}
     */
    @Override
    public Car update(Long id, Car car) {
        return carRepository.findById(id)
                .map(car_temp -> {
                    if (car.getCClass() != null) {
                        try {
                            CarClass carClass = carClassRepository.findByNameEquals(car.getCClass());
                            car_temp.setCarClass(carClass);
                            car_temp.setCClass(carClass.getName());
                        } catch (NullPointerException e) {
                            throw new ResourceNotFoundException("error");
                        }
                    }

                    if (car.getCType() != null) {
                        try {
                            CarType carType = carTypeRepository.findByNameEquals(car.getCType());
                            car_temp.setCarType(carType);
                            car_temp.setCType(carType.getName());
                        } catch (NullPointerException e) {
                            throw new ResourceNotFoundException("error");
                        }
                    }

                    if (car.getNumber() != null) {
                        car_temp.setNumber(car.getNumber());
                    }

                    return carRepository.save(car_temp);
                }).orElseThrow(() -> new ResourceNotFoundException("error"));
    }

    /**
     * Функция поиска вагона по переданному id
     * @param id Long параметр id вагона
     * @return возвращает вагон с указанным id {@link Car}
     */
    @Override
    public Car getCarById(Long id) {
        return carRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("error"));
    }

    /**
     * Функция поиска всех вагонов
     * @param pageable
     * @return возвращает список всех вагонов в формате Pageable {@link Pageable}
     */
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
