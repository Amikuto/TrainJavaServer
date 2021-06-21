package uni.ami.restdb.serviseImpl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Pageable;
import uni.ami.restdb.model.Seat;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@EnableAutoConfiguration
@Configuration
class SeatServiceImplTest {

    @Resource
    @Autowired
    private SeatServiceImpl service;

    @Test
    void saveAndDelete() {
        Seat testClass = new Seat(1000L, 1, 1);
        try {
            service.save(testClass, testClass.getId());
        } finally {
            service.delete(testClass.getId());
        }
    }

    @Test
    void update() {
        Seat testClass = new Seat(1000L, 1, 1);
        try {
            Seat test1 = new Seat(2000L, 2, 2);
            test1.setNumber(3);
            service.update(testClass.getId(), test1);
        } finally {
            service.delete(testClass.getId());
        }
    }

    @Test
    void getSeatById() {
        Seat testClass = new Seat(1000L, 1, 1);
        service.save(testClass, testClass.getId());
        try {
            service.getSeatById(testClass.getId());
        } finally {
            service.delete(testClass.getId());
        }
    }

    @Test
    void getAll() {
        service.getAll(Pageable.unpaged());
    }
}