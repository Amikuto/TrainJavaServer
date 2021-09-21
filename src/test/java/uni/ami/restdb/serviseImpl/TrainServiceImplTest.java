package uni.ami.restdb.serviseImpl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Pageable;
import uni.ami.restdb.model.AuditModel;
import uni.ami.restdb.model.City;
import uni.ami.restdb.model.Station;
import uni.ami.restdb.model.Train;

import javax.annotation.Resource;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@EnableAutoConfiguration
@Configuration
class TrainServiceImplTest {

//    @Resource
//    @Autowired
//    private TrainServiceImpl service;
//
//    @Test
//    void saveAndDelete() {
//        Train testClass = new Train(1000L, LocalDate.parse("2020-04-12"), LocalDate.parse("2020-04-12"), "Moscow", "Kazan");
//        try {
//            service.save(testClass);
//        } finally {
//            service.delete(testClass.getId());
//        }
//    }
//
//    @Test
//    void update() {
//        Train testClass = new Train(1000L, LocalDate.parse("2020-04-12"), LocalDate.parse("2020-04-12"), "Moscow", "Kazan");
//        try {
//            Train test1 = new Train(2000L, LocalDate.parse("2020-04-12"), LocalDate.parse("2020-04-12"), "Kazan", "Moscow");
//            test1.setArrivalCity("TestName3");
//            service.update(testClass.getId(), test1);
//        } finally {
//            service.delete(testClass.getId());
//        }
//    }
//
//    @Test
//    void getTrainById() {
//        Train testClass = new Train(1000L, LocalDate.parse("2020-04-12"), LocalDate.parse("2020-04-12"), "Moscow", "Kazan");
//        service.save(testClass);
//        try {
//            service.getTrainById(testClass.getId());
//        } finally {
//            service.delete(testClass.getId());
//        }
//    }
//
//    @Test
//    void getAll() {
//        service.getAll(Pageable.unpaged());
//    }
}