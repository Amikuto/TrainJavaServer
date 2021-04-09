package uni.ami.restdb.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import uni.ami.restdb.model.Seat;

import java.util.List;

public interface SeatService {

    Seat save(Seat seat, Long id);

    ResponseEntity<?> delete(Long id);

    Seat update(Long id, Seat seat);

    Seat getSeatById(Long id);

    Page<Seat> getAll(Pageable pageable);

    List<Seat> findAllByCarId(Long id);

    List<Seat> findAllByTicketId(Long id);

    List<Seat> findAllByCost(Integer cost);
}
