package uni.ami.restdb.ServiseImpl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uni.ami.restdb.exceptions.FindException;
import uni.ami.restdb.model.City;
import uni.ami.restdb.repository.CityRepository;
import uni.ami.restdb.service.CityService;

@Slf4j
@Service
public class CityServiceImpl implements CityService {

    @Autowired
    CityRepository cityRepository;

    @Override
    public City save(City city) {
        return cityRepository.save(city);
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        City city = cityRepository.findById(id).orElseThrow(FindException::new);
        cityRepository.delete(city);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public City update(Long id, City city) {
        return cityRepository.findById(id)
                .map(city_temp -> {
                    city_temp.setName(city.getName());
                    city_temp.setStations(city.getStations());

                    return cityRepository.save(city_temp);
                }).orElseThrow(FindException::new);
    }

    @Override
    public City getCityById(Long id) {
        return cityRepository.findById(id).orElseThrow(FindException::new);
    }

    @Override
    public Page<City> getAll(Pageable pageable) {
        return cityRepository.findAll(pageable);
    }

    @Override
    public City getCityByName(String name) {
        return cityRepository.findByNameEquals(name);
    }
}
