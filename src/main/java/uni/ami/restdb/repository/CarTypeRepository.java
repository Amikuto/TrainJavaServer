package uni.ami.restdb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uni.ami.restdb.model.CarType;

public interface CarTypeRepository extends JpaRepository<CarType, Long> {
}
