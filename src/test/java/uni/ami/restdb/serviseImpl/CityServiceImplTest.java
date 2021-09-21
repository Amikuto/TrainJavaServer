package uni.ami.restdb.serviseImpl;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Pageable;
import uni.ami.restdb.model.AuditModel;
import uni.ami.restdb.model.City;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@EnableAutoConfiguration
@Configuration
class CityServiceImplTest {

//    @Resource
//    @Autowired
//    private CityServiceImpl cityService;
//
//    @Test
//    void saveAndDelete() {
//        City city = new City(1000L, "Test");
//        try {
//            cityService.save(city);
//        } finally {
//            cityService.delete(city.getId());
//        }
//    }
//
//    @Test
//    void update() {
//        City city = new City(1000L, "Test");
//        try {
//            City city1 = new City(2000L, "2ndCityName");
//            city1.setName("AnotherTestName");
//            cityService.update(city.getId(), city1);
//        } finally {
//            cityService.delete(city.getId());
//        }
//    }
//
//    @Test
//    void getCityById() {
//        City city = new City(1000L, "Test");
//        cityService.save(city);
//        try {
//            cityService.getCityById(city.getId());
//        } finally {
//            cityService.delete(city.getId());
//        }
//    }
//
//    @Test
//    void getAll() { cityService.getAll(Pageable.unpaged()); }
}