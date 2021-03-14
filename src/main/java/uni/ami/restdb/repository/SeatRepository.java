package uni.ami.restdb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uni.ami.restdb.model.Seat;

public interface SeatRepository extends JpaRepository<Seat, Long> {
}
