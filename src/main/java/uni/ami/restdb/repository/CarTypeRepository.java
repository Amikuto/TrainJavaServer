package uni.ami.restdb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uni.ami.restdb.model.CarType;

/**
 * Репозиторий типов вагона
 * @author damir
 */
@Repository
public interface CarTypeRepository extends JpaRepository<CarType, Long> {
    CarType findByNameEquals(String name);
}
