package uni.ami.restdb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import uni.ami.restdb.model.Car;
import uni.ami.restdb.model.Train;
import uni.ami.restdb.repository.CarRepository;
import uni.ami.restdb.repository.StationRepository;
import uni.ami.restdb.repository.TrainRepository;

import javax.validation.Valid;
import java.util.List;

@RestController
public class CarController {

    @Autowired
    private TrainRepository trainRepository;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private StationRepository stationRepository;

    @GetMapping("/trains/{trainId}/cars")
    public List<Car> getCarsByTrainId(@PathVariable Long trainId) {
        System.out.println(carRepository.findAllByTrainId(trainId));
        return carRepository.findAllByTrainId(trainId);
    }

    @PostMapping("/cars")
    public List<Car> addCar(@Valid @RequestBody Car car) {
        return null;
    }
}
