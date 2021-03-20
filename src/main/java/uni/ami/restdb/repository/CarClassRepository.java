package uni.ami.restdb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uni.ami.restdb.model.CarClass;

public interface CarClassRepository extends JpaRepository<CarClass, Long> {
}
