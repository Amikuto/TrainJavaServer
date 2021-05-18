package uni.ami.restdb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uni.ami.restdb.model.City;

import java.util.List;

/**
 * Репозиторий городов
 * @author damir
 */
@Repository
public interface CityRepository extends JpaRepository<City, Long> {
    City findByNameEquals(String name);
}
