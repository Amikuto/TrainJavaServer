package uni.ami.restdb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uni.ami.restdb.serviseImpl.CityServiceImpl;
import uni.ami.restdb.model.City;

import javax.validation.Valid;

/**
 * Контроллер городов
 * @author damir
 */
@RestController
public class CityController {

    @Autowired
    private CityServiceImpl cityService;

    /**
     * Контроллер GET запроса по ссылке "/cities"
     * для получения информации о всех городах
     * @param pageable параметр заглушка
     * @return возвращает страницу со всеми городами из базы данных
     */
    @GetMapping("/cities")
    public Page<City> getAllCities(Pageable pageable){
        return cityService.getAll(pageable);
    }

    /**
     * Контроллер GET запроса по ссылке "/cities/{name}"
     * для полученя информации города по его имени
     * @param name название города
     * @return возвращает класс города по заданному имени
     */
    @GetMapping("/cities/{name}")
    public City getCityByName(@PathVariable String name) {
        return cityService.getCityByName(name);
    }

    /**
     * Контроллер POST запроса по ссылке "/cities"
     * для добавления нового города в базе данных
     * @param city JSON данные о городе
     * @return возвращает сохраненный класс города
     */
    @PostMapping("/cities")
    public City addCity(@Valid @RequestBody City city) {
        return cityService.save(city);
    }

    /**
     * Контроллер PUT запроса по ссылке "cities/{cityId}"
     * для изменения информации о существующем городе
     * @param cityId параметр id города
     * @param city JSON новые данные о городе
     * @return возвращает измененный класс города
     */
    @PutMapping("/cities/{cityId}")
    public City updateCity(@Valid @PathVariable Long cityId,
                           @Valid @RequestBody City city) {
        return cityService.update(cityId, city);
    }

    /**
     * Контроллер DELETE запроса по ссылке "cities/{cityId}"
     * для удаления города из базы данных
     * @param cityId параметр id города
     * @return возвращает код статуса
     */
    @DeleteMapping("/cities/{cityId}")
    public ResponseEntity<?> deleteCity(@Valid @PathVariable Long cityId) {
        return cityService.delete(cityId);
    }
}
