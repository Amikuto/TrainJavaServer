package uni.ami.restdb.serviseImpl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Pageable;
import uni.ami.restdb.model.AuditModel;
import uni.ami.restdb.model.Train;
import uni.ami.restdb.model.User;

import javax.annotation.Resource;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@EnableAutoConfiguration
@Configuration
class UserServiceImplTest {

//    @Resource
//    @Autowired
//    private UserServiceImpl service;
//
//    @Test
//    void saveAndDelete() {
//        User testClass = new User(1000L, "fullName", "email", "login", "password");
//        try {
//            service.save(testClass);
//        } finally {
//            service.delete(testClass.getId());
//        }
//    }
//
//    @Test
//    void update() {
//        User testClass = new User(1000L, "fullName", "email", "login", "password");
//        try {
//            User test1 = new User(2000L, "fullName2", "email2", "login2", "password2");
//            test1.setLogin("testLogin3");
//            service.update(testClass.getId(), test1);
//        } finally {
//            service.delete(testClass.getId());
//        }
//    }
//
//    @Test
//    void getUserById() {
//        User testClass = new User(1000L, "fullName", "email", "login", "password");
//        service.save(testClass);
//        try {
//            service.getUserById(testClass.getId());
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