package uni.ami.restdb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.persistence.Entity;

@SpringBootApplication
@EnableJpaAuditing  // Аудит БД
public class RestdbApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestdbApplication.class, args);
    }

}
