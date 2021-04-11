package uni.ami.restdb.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import uni.ami.restdb.model.City;
import uni.ami.restdb.model.Train;

public interface CityService {

    City save(City city);

    ResponseEntity<?> delete(Long id);

    City update(Long id, City city);

    City getCityById(Long id);

    Page<City> getAll(Pageable pageable);

    City getCityByName(String name);
}
