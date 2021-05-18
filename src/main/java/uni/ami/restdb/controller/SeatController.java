package uni.ami.restdb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uni.ami.restdb.serviseImpl.SeatServiceImpl;
import uni.ami.restdb.model.Seat;

import javax.validation.Valid;
import java.util.List;

/**
 * Контроллер мест
 * @author damir
 */
@RestController
public class SeatController {

    @Autowired
    SeatServiceImpl seatService;

    /**
     * Контроллер GET запроса по ссылке "/seats"
     * для получения информации о всех местах
     * @param pageable параметр заглушка
     * @return возвращает страницу со всеми местами из базы данных
     */
    @GetMapping("/seats")
    public Page<Seat> getAllSeats(Pageable pageable) {
        return seatService.getAll(pageable);
    }

    /**
     * Поиск места по билету
     * @param ticketId параметр id билета
     * @return возвращает список мест
     */
    @GetMapping("/seats/{ticketId}/tickets")
    public List<Seat> findAllById(@PathVariable Long ticketId) {
        return seatService.findAllByTicketId(ticketId);
    }

    /**
     * Поиск мест по цене
     * @param cost параметр цены
     * @return возвращает список мест
     */
    @GetMapping("/seats/{cost}/costs")
    public List<Seat> findAllByCost(@PathVariable Integer cost) {
        return seatService.findAllByCost(cost);
    }

    /**
     * Контроллер GET запроса по ссылке "/seats/{carId}/cars"
     * для полученя информации места по id вагона, в котором он расположен
     * @param carId параметр id вагона
     * @return возвращает список мест
     */
    @GetMapping("/seats/{carId}/cars")
    public List<Seat> findAllByCar(@PathVariable Long carId) {
        return seatService.findAllByCarId(carId);
    }

    /**
     * Контроллер POST запроса по ссылке "/seats/{carId}"
     * для добавления нового места в базу данных
     * @param carId параметр id вагона
     * @param seat JSON данные о месте
     * @return возвращает сохраненный класс места
     */
    @PostMapping("/seats/{carId}")
    public Seat addSeat(@PathVariable Long carId,
                        @Valid @RequestBody Seat seat) {
        return seatService.save(seat, carId);
    }

    /**
     * Контроллер PUT запроса по ссылке "/seats/{seatId}"
     * для изменения информации о существующем месте
     * @param seatId параметр id места
     * @param seat JSON новые данные о месте
     * @return возвращает измененный класс места
     */
    @PutMapping("/seats/{seatId}")
    public Seat addCar(@Valid @PathVariable Long seatId,
                       @Valid @RequestBody Seat seat) {
        return seatService.update(seatId, seat);
    }

    /**
     * Контроллер DELETE запроса по ссылке "/seats/{seatId}"
     * для удаления места из базы данных
     * @param seatId параметр id места
     * @return возвращает код статуса
     */
    @DeleteMapping("/seats/{seatId}")
    public ResponseEntity<?> deleteSeat(@Valid @PathVariable Long seatId) {
        return seatService.delete(seatId);
    }
}
