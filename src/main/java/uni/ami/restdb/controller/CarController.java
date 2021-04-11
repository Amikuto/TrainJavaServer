package uni.ami.restdb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uni.ami.restdb.ServiseImpl.CarServiceImpl;
import uni.ami.restdb.model.Car;

import javax.validation.Valid;
import java.util.List;

@RestController
public class CarController {

//    @Autowired
//    private TrainRepository trainRepository;
//
//    @Autowired
//    private CarRepository carRepository;
//
//    @Autowired
//    private StationRepository stationRepository;

    @Autowired
    private CarServiceImpl carService;

    @GetMapping("/cars")
    public Page<Car> getAllCars(Pageable pageable) {
        return carService.getAll(pageable);
    }

    @GetMapping("/cars/{trainId}/trains")
    public List<Car> getCarsByTrainId(@PathVariable Long trainId) {
        return carService.findAllByTrainId(trainId);
    }

    @GetMapping("/cars/trains/{trainId}/type/{typeId}")
    public List<Car> getCarsByTrainId(@PathVariable Long trainId,
                                      @PathVariable Long typeId) {
        return carService.findAllByTrainAndTypeIds(trainId, typeId);
    }

    @GetMapping("/cars/{trainId}/trains/{typeId}/type/{classId}/class")
    public List<Car> getCarsByTrainId(@PathVariable Long trainId,
                                      @PathVariable Long typeId,
                                      @PathVariable Long classId) {
        return carService.findAllByTrainAndClassAndTypeIds(trainId, classId, typeId);
    }

    @PostMapping("/cars")
    public Car addCar(@Valid @RequestBody Car car) {
        return carService.save(car);
    }

    @PutMapping("/cars/{carId}")
    public Car updateCar(@Valid @PathVariable Long carId,
                         @Valid @RequestBody Car car) {
        return carService.update(carId, car);
    }

    @DeleteMapping("/cars/{carId}")
    public ResponseEntity<?> deleteCar(@Valid @PathVariable Long carId) {
        return carService.delete(carId);
    }
}
