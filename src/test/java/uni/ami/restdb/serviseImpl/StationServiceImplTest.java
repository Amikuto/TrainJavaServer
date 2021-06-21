package uni.ami.restdb.serviseImpl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Pageable;
import uni.ami.restdb.model.City;
import uni.ami.restdb.model.Seat;
import uni.ami.restdb.model.Station;

import javax.annotation.Resource;

@SpringBootTest
@EnableAutoConfiguration
@Configuration
class StationServiceImplTest {

    @Resource
    @Autowired
    private StationServiceImpl service;

    @Test
    void saveAndDelete() {
        City city = new City(1000L, "TestCityName");
        Station testClass = new Station(1000L, "TestName", city);
        try {
            service.save(testClass, city.getName());
        } finally {
            service.delete(testClass.getId());
        }
    }

    @Test
    void update() {
        City city = new City(1000L, "TestCityName");
        Station testClass = new Station(1000L, "TestName", city);
        try {
            Station test1 = new Station(2000L, "TestName2", city);
            test1.setName("TestName3");
            service.update(testClass.getId(), test1);
        } finally {
            service.delete(testClass.getId());
        }
    }

    @Test
    void getStationById() {
        City city = new City(1000L, "TestCityName");
        Station testClass = new Station(1000L, "TestName", city);
        service.save(testClass, city.getName());
        try {
            service.getStationById(testClass.getId());
        } finally {
            service.delete(testClass.getId());
        }
    }

    @Test
    void getAll() {
        service.getAll(Pageable.unpaged());
    }
}