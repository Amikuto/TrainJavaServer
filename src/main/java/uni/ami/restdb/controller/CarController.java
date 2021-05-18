package uni.ami.restdb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uni.ami.restdb.serviseImpl.CarServiceImpl;
import uni.ami.restdb.model.Car;

import javax.validation.Valid;
import java.util.List;

/**
 * Контроллер вагонов
 * @author damir
 */
@RestController
public class CarController {

    @Autowired
    private CarServiceImpl carService;

    /**
     * Контроллер GET запроса по ссылке "/cars"
     * для получения информации о всех вагонах
     * @param pageable параметр заглушка
     * @return возвращает страницу со всеми вагонами из базы данных
     */
    @GetMapping("/cars")
    public Page<Car> getAllCars(Pageable pageable) {
        return carService.getAll(pageable);
    }

    /**
     * Контроллер GET запроса по ссылке "/cars/{trainId}/trains"
     * для полученя информации вагона по id поезда в котором он находится
     * @param trainId параметр id поезда
     * @return возвращает список вагонов
     */
    @GetMapping("/cars/{trainId}/trains")
    public List<Car> getCarsByTrainId(@PathVariable Long trainId) {
        return carService.findAllByTrainId(trainId);
    }

    @GetMapping("/cars/trains/{trainId}/type/{typeId}")
    public List<Car> findAllByTrainAndTypeId(@PathVariable Long trainId,
                                      @PathVariable Long typeId) {
        return carService.findAllByTrainAndTypeIds(trainId, typeId);
    }

    @GetMapping("/cars/{trainId}/trains/{typeId}/type/{classId}/class")
    public List<Car> findAllByTrainAndClassAndTypeId(@PathVariable Long trainId,
                                      @PathVariable Long typeId,
                                      @PathVariable Long classId) {
        return carService.findAllByTrainAndClassAndTypeIds(trainId, classId, typeId);
    }

    /**
     * Контроллер POST запроса по ссылке "/cars"
     * для добавления нового вагона в базу данных
     * @param car JSON данные о вагоне
     * @return возвращает сохраненный класс вагона
     */
    @PostMapping("/cars")
    public Car addCar(@Valid @RequestBody Car car) {
        return carService.save(car);
    }

    /**
     * Контроллер PUT запроса по ссылке "/cars/{carId}"
     * для изменения информации о существующем вагоне
     * @param carId параметр id вагона
     * @param car JSON новые данные о вагоне
     * @return возвращает измененный класс вагона
     */
    @PutMapping("/cars/{carId}")
    public Car updateCar(@Valid @PathVariable Long carId,
                         @Valid @RequestBody Car car) {
        return carService.update(carId, car);
    }

    /**
     * Контроллер DELETE запроса по ссылке "/cars/{carId}"
     * для удаления вагона из базы данных
     * @param carId параметр id вагона
     * @return возвращает код статуса
     */
    @DeleteMapping("/cars/{carId}")
    public ResponseEntity<?> deleteCar(@Valid @PathVariable Long carId) {
        return carService.delete(carId);
    }
}
