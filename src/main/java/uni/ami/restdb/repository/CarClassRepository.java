package uni.ami.restdb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uni.ami.restdb.model.CarClass;

/**
 * Репозиторий классов вагона
 * @author damir
 */
@Repository
public interface CarClassRepository extends JpaRepository<CarClass, Long> {
    CarClass findByNameEquals(String name);
}
