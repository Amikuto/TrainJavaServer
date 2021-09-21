package uni.ami.restdb.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import uni.ami.restdb.RestdbApplication;
import uni.ami.restdb.model.AuditModel;
import uni.ami.restdb.model.Car;
import uni.ami.restdb.repository.CarRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@SpringBootTest(
//        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
//        classes = RestdbApplication.class)
//@AutoConfigureMockMvc
//@TestPropertySource("classpath:application_test.properties")
class CarControllerTest extends AuditModel {

//    @Autowired
//    MockMvc mvc;
//
//    @Autowired
//    CarRepository repository;
//
//    @BeforeEach
//    void beforeEach() throws Exception {
//        Car car = new Car(1123L, 1123);
//        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
//        map.put(car.getId().toString(), new ArrayList<>(1));
//        mvc.perform(MockMvcRequestBuilders
//                .post("/cars")
//                .params(map)
//                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk());
//    }
//
//    @AfterEach
//    void afterEach() {
//        repository.deleteAll();
//    }
//
//    @Test
//    void getAllCars() throws Exception {
//        Car car = repository.getOne(1123L);
//        MultiValueMap<String, String> data = new LinkedMultiValueMap<>();
//        data.add("id", car.getId().toString());
//        mvc.perform(MockMvcRequestBuilders
//                .post("/cars")
//                .params(data)
//                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk());
//        mvc.perform(MockMvcRequestBuilders
//                .post("/cars")
//                .param("id", car.getId().toString())
//                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk());
//        assertEquals(1, repository.count());
//    }
//
//    @Test
//    void addCar() throws Exception {
//        Car car1 = new Car(1000L, 1);
//        Car car2 = new Car(2000L, 2);
//        Car car3 = new Car(3000L, 3);
//        MultiValueMap<String, String> data = new LinkedMultiValueMap<>();
//        data.add("id", car1.getId().toString());
//        data.add("id", car2.getId().toString());
//        data.add("id", car3.getId().toString());
//        ResultMatcher[] expectations = new ResultMatcher[] {
//                status().isOk(),
//                status().isOk(),
//                status().isOk()
//        };
//        for (int i=0;i<data.size();i++) {
//            mvc.perform(MockMvcRequestBuilders
//                    .post("/car")
//                    .contentType(MediaType.APPLICATION_FORM_URLENCODED)
//                    .accept(MediaType.APPLICATION_JSON))
//                    .andExpect(expectations[i]);
//        }
//        assertEquals(3, repository.count());
//    }
//
//    @Test
//    void deleteCar() throws Exception {
//        Car car = repository.getOne(1123L);
//        MultiValueMap<String, String> data = new LinkedMultiValueMap<>();
//        data.add("id", car.getId().toString());
//        mvc.perform(MockMvcRequestBuilders
//                .post("/cars")
//                .params(data)
//                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk());
//        assertEquals(0, repository.count());
//    }
}