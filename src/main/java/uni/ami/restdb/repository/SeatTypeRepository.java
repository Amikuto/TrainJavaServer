package uni.ami.restdb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uni.ami.restdb.model.SeatType;

/**
 * Репозиторий типов мест
 * @author damir
 */
public interface SeatTypeRepository extends JpaRepository<SeatType, Long> {
    SeatType findByNameEquals(String name);
}
