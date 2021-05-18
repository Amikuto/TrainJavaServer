package uni.ami.restdb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * Класс запуска приложения
 */
@SpringBootApplication
@EnableJpaAuditing
public class RestdbApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestdbApplication.class, args);
    }

}
