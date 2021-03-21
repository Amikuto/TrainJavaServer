package uni.ami.restdb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uni.ami.restdb.model.Seat;
import uni.ami.restdb.model.Ticket;

import java.util.List;

public interface SeatRepository extends JpaRepository<Seat, Long> {
    List<Seat> findAllByCostEquals(Integer cost);
    List<Seat> findAllByTicketIdEquals(Long ticket_id);
    List<Seat> findAllByCarIdEquals(Long car_id);
}
