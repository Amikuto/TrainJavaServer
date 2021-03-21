package uni.ami.restdb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import uni.ami.restdb.ServiseImpl.SeatServiceImpl;
import uni.ami.restdb.exceptions.FindException;
import uni.ami.restdb.model.Seat;
import uni.ami.restdb.repository.SeatRepository;
import uni.ami.restdb.service.SeatService;

import java.util.List;

@RestController
public class SeatController {

    @Autowired
    SeatServiceImpl seatService;

    @Autowired
    SeatRepository seatRepository;

    @GetMapping("/seats")
    public Page<Seat> getAllSeats(Pageable pageable) {
        return seatService.getAll(pageable);
    }

    @GetMapping("/seats/tickets/{ticketId}")
    public List<Seat> findAllByCost(@PathVariable Long ticketId) {
        return seatService.findAllByTicketId(ticketId);
    }

    @GetMapping("/seats/costs/{cost}")
    public List<Seat> findAllByCost(@PathVariable Integer cost) {
        return seatService.findAllByCost(cost);
    }

    @GetMapping("/seats/cars/{carId}")
    public List<Seat> findAllByCar(@PathVariable Long carId) {
        return seatService.findAllByCarId(carId);
    }
}
