package uni.ami.restdb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uni.ami.restdb.ServiseImpl.SeatServiceImpl;
import uni.ami.restdb.exceptions.FindException;
import uni.ami.restdb.model.Car;
import uni.ami.restdb.model.Seat;
import uni.ami.restdb.repository.SeatRepository;
import uni.ami.restdb.service.SeatService;

import javax.validation.Valid;
import java.util.List;

@RestController
public class SeatController {

    @Autowired
    SeatServiceImpl seatService;

//    @Autowired
//    SeatRepository seatRepository;

    @GetMapping("/seats")
    public Page<Seat> getAllSeats(Pageable pageable) {
        return seatService.getAll(pageable);
    }

    @GetMapping("/seats/{ticketId}/tickets")
    public List<Seat> findAllByCost(@PathVariable Long ticketId) {
        return seatService.findAllByTicketId(ticketId);
    }

    @GetMapping("/seats/{cost}/costs")
    public List<Seat> findAllByCost(@PathVariable Integer cost) {
        return seatService.findAllByCost(cost);
    }

    @GetMapping("/seats/{carId}/cars")
    public List<Seat> findAllByCar(@PathVariable Long carId) {
        return seatService.findAllByCarId(carId);
    }

    @PostMapping("/seats/{carId}")
    public Seat addSeat(@PathVariable Long carId,
                        @Valid @RequestBody Seat seat) {
        return seatService.save(seat, carId);
    }

    @PutMapping("/seats/{seatId}")
    public Seat addCar(@Valid @PathVariable Long seatId,
                       @Valid @RequestBody Seat seat) {
        return seatService.update(seatId, seat);
    }

    @DeleteMapping("/seats/{seatId}")
    public ResponseEntity<?> deleteSeat(@Valid @PathVariable Long seatId) {
        return seatService.delete(seatId);
    }
}
