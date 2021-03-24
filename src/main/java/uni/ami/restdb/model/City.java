package uni.ami.restdb.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "city")
public class City {

    @Id
    @GeneratedValue(generator = "city_generator")
    @SequenceGenerator(
            name = "city_generator",
            sequenceName = "city_sequence",
            initialValue = 10
    )
    private Long id;

    public Long getId() {
        return id;
    }



}
