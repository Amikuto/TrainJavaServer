package uni.ami.restdb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uni.ami.restdb.ServiseImpl.CityServiceImpl;
import uni.ami.restdb.model.City;

import javax.validation.Valid;

@RestController
public class CityController {

    @Autowired
    private CityServiceImpl cityService;

    @GetMapping("/cities")
    public Page<City> getAllCities(Pageable pageable){
        return cityService.getAll(pageable);
    }

    @GetMapping("/cities/{name}")
    public City getCityByName(@PathVariable String name) {
        return cityService.getCityByName(name);
    }

    @PostMapping("/cities")
    public City addCity(@Valid @RequestBody City city) {
        return cityService.save(city);
    }

    @PutMapping("/cities/{cityId}")
    public City updateCity(@Valid @PathVariable Long cityId,
                           @Valid @RequestBody City city) {
        return cityService.update(cityId, city);
    }

    @DeleteMapping("/cities/{cityId}")
    public ResponseEntity<?> deleteCity(@Valid @PathVariable Long cityId) {
        return cityService.delete(cityId);
    }
}
