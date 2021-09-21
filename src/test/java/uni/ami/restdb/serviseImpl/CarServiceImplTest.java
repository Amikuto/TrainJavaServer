package uni.ami.restdb.serviseImpl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Pageable;
import uni.ami.restdb.model.AuditModel;
import uni.ami.restdb.model.Car;
import uni.ami.restdb.model.City;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@EnableAutoConfiguration
@Configuration
class CarServiceImplTest {

//    @Resource
//    @Autowired
//    private CarServiceImpl carService;
//
//    @Test
//    void saveAndDelete() {
//        Car car = new Car(1000L, 1);
//        try {
//            carService.save(car);
//        } finally {
//            carService.delete(car.getId());
//        }
//    }
//
//    @Test
//    void update() {
//        Car car = new Car(1000L, 1);
//        try {
//            Car car1 = new Car(2000L, 2);
//            car1.setNumber(3);
//            carService.update(car.getId(), car1);
//        } finally {
//            carService.delete(car.getId());
//        }
//    }
//
//    @Test
//    void getCarById() {
//        Car car = new Car(1000L, 1);
//        carService.save(car);
//        try {
//            carService.getCarById(car.getId());
//        } finally {
//            carService.delete(car.getId());
//        }
//    }
//
//    @Test
//    void getAll() {
//        carService.getAll(Pageable.unpaged());
//    }
}