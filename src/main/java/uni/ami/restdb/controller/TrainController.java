package uni.ami.restdb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uni.ami.restdb.serviseImpl.TrainServiceImpl;
import uni.ami.restdb.model.Train;

import javax.validation.Valid;
import java.util.List;

/**
 * Контроллер поездов
 * @author damir
 */
@RestController
public class TrainController {

    @Autowired
    private TrainServiceImpl trainService;

    /**
     * Контроллер GET запроса по ссылке "/trains"
     * для получения информации о всех поездах
     * @param pageable параметр заглушка
     * @return возвращает страницу со всеми поездами из базы данных
     */
    @GetMapping("/trains")
    public Page<Train> getAllTrains(Pageable pageable) {
        return trainService.getAll(pageable);
    }

    @GetMapping("/trains/{trainId}")
    public Train getTrainById(@PathVariable Long trainId) {
        return trainService.getTrainById(trainId);
    }

    @GetMapping("/trains/{depStationId}/{arrStationId}/stations")
    public List<Train> getTrainsByDepartingAndArrivingStation(@PathVariable Long arrStationId,
                                                              @PathVariable Long depStationId) {
        return trainService.findAllByDepartingStationAndArrivingStation(depStationId, arrStationId);
    }

    @GetMapping("/trains/0/{arrStationId}/stations/")
    public List<Train> getTrainsByArrStation(@PathVariable Long arrStationId) {
        return trainService.findAllByArrStationId(arrStationId);
    }

    @GetMapping("/trains/{depStationId}/0/stations")
    public List<Train> getTrainsByDepStation(@PathVariable Long depStationId) {
        return trainService.findAllByDepStationId(depStationId);
    }

    /**
     * Контроллер GET запроса по ссылке "/trains/search/{depStationName}/{arrStationName}/{depDate}"
     * для полученя информации о поездах, которые отправляются и прибывают в заданных станциях в заданную дату
     * @param depStationName параметр станции отправления
     * @param arrStationName параметр станции прибытия
     * @param depDate параметр даты отправления
     * @return возвращает список поездов
     */
    @GetMapping("/trains/search/{depStationName}/{arrStationName}/{depDate}")
    public List<Train> getTrainsByDepartingAndArrivingStation(@PathVariable String depStationName,
                                                              @PathVariable String arrStationName,
                                                              @PathVariable String depDate) {
        return trainService.findAllByArrivingStationAndDepartingStationAndDate(depStationName, arrStationName, depDate);
    }

    /**
     * Контроллер GET запроса по ссылке "/trains/data/year-statistic/{date}"
     * для полученя информации о поездах
     * @param date параметр даты
     * @return возвращает список поездов
     */
    @GetMapping("/trains/data/year-statistic/{date}")
    public List<Train> findAllByYearDep(@PathVariable String date) {
        return trainService.findAllByYearDep(date);
    }

    @GetMapping("/trains/data/year-statistic-count/{date}")
    public Integer findCountOfTrainsByDateDepIsLike(@PathVariable String date) {
        return trainService.findCountOfTrainsByDateDepIsLike(date);
    }

    @GetMapping(value = "/trains/data/train-sold-tickets-data/{trainId}")
    public Integer valueOfSoldTicketsDataByTrainId(@PathVariable Long trainId) {
        return trainService.valueOfSoldTicketsDataByTrainId(trainId);
    }

    @GetMapping(value = "/trains/data/train-notsold-tickets-data/{trainId}")
    public Integer valueOfNotSoldTicketsDataByTrainId(@PathVariable Long trainId) {
        return trainService.valueOfNotSoldTicketsDataByTrainId(trainId);
    }

    @GetMapping(value = "/trains/data/train-all-tickets-data/{trainId}")
    public Integer valueOfAllTicketsDataByTrainId(@PathVariable Long trainId) {
        return trainService.valueOfAllTicketsDataByTrainId(trainId);
    }

    /**
     * Контроллер POST запроса по ссылке "/trains"
     * для добавления нового поезда в базу данных
     * @param train JSON данные о поезде
     * @return возвращает сохраненный класс поезда
     */
    @PostMapping("/trains")
    public Train addTrain(@Valid @RequestBody Train train) {
        return trainService.save(train);
    }

    /**
     * Контроллер PUT запроса по ссылке "/trains/{trainId}"
     * для изменения информации о существующем поезде
     * @param trainId параметр id поезда
     * @param train JSON новые данные о поезде
     * @return возвращает измененный класс поезда
     */
    @PutMapping("/trains/{trainId}")
    public Train updateTrain(@PathVariable Long trainId,
                             @Valid @RequestBody Train train) {
        return trainService.update(trainId, train);
    }

    /**
     * Контроллер DELETE запроса по ссылке "/trains/{trainId}"
     * для удаления вагона из базы данных
     * @param trainId параметр id поезда
     * @return возвращает код статуса
     */
    @DeleteMapping("/trains/{trainId}")
    public ResponseEntity<?> deleteTrain(@PathVariable Long trainId) {
        return trainService.delete(trainId);
    }
}
