package uni.ami.restdb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uni.ami.restdb.model.Seat;
import uni.ami.restdb.model.Ticket;

import java.util.List;

/**
 * Репозиторий мест
 * @author damir
 */
@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> {
    List<Seat> findAllByCostEquals(Integer cost);
    List<Seat> findAllByTicketIdEquals(Long ticket_id);
    List<Seat> findAllByCarIdEquals(Long car_id);
}
