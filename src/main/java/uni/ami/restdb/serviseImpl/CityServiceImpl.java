package uni.ami.restdb.serviseImpl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uni.ami.restdb.exceptions.FindException;
import uni.ami.restdb.exceptions.ResourceNotFoundException;
import uni.ami.restdb.model.Car;
import uni.ami.restdb.model.City;
import uni.ami.restdb.repository.CityRepository;
import uni.ami.restdb.service.CityService;

/**
 * Класс сервиса городов
 * @author damir
 */
@Slf4j
@Service
public class CityServiceImpl implements CityService {

    @Autowired
    CityRepository cityRepository;

    /**
     * Функция сохранения города в базе данных
     * @param city принимает класс города для сохранения {@link City}
     * @return возвразает сохраненный город {@link City}
     */
    @Override
    public City save(City city) {
        return cityRepository.save(city);
    }

    /**
     * Функция удаления города из базы данных
     * @param id принимет в качестве параметра id города
     * @return возвращает HttpStatus.OK {@link ResponseEntity}
     */
    @Override
    public ResponseEntity<?> delete(Long id) {
        City city = cityRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Город с заданным id не найден!"));
        cityRepository.delete(city);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Функция изменения информации о городе в базе данных
     * @param id принимет в качестве параметра id города
     * @param city принимет в качестве параметра класс города для изменения данных {@link City}
     * @return возвращает класс города с измененной информацией {@link City}
     */
    @Override
    public City update(Long id, City city) {
        return cityRepository.findById(id)
                .map(city_temp -> {
                    if (city.getName() != null) {
                        city_temp.setName(city.getName());
                    }

                    return cityRepository.save(city_temp);
                }).orElseThrow(() -> new ResourceNotFoundException("Город с заданным id не найден!"));
    }

    /**
     * Функция поиска города по id
     * @param id принимет в качестве параметра id города
     * @return возвращает класс города {@link City}
     */
    @Override
    public City getCityById(Long id) {
        return cityRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Город с заданным id не найден!"));
    }

    /**
     * Функция поиска всех городов в базе данных
     * @param pageable
     * @return возвращает список всех городов в формате Pageable {@link Pageable}
     */
    @Override
    public Page<City> getAll(Pageable pageable) {
        return cityRepository.findAll(pageable);
    }

    /**
     * Функция поиска города по названию
     * @param name принимет в качестве параметра имя города {@link City#name}
     * @return возвращает класс города {@link City}
     */
    @Override
    public City getCityByName(String name) {
        return cityRepository.findByNameEquals(name);
    }
}
