package uni.ami.restdb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import uni.ami.restdb.model.Car;
import uni.ami.restdb.repository.CarRepository;
import uni.ami.restdb.repository.StationRepository;
import uni.ami.restdb.repository.TrainRepository;

import java.util.List;

@RestController
public class CarController {

    @Autowired
    private TrainRepository trainRepository;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private StationRepository stationRepository;

    @GetMapping("/stations/{stationId}/trains/{trainId}/cars")
    public List<Car> getCarsByTrainId(@PathVariable Long stationId,
                                      @PathVariable Long trainId) {
        return carRepository.findByTrainId(trainRepository.findById());
    }
}
