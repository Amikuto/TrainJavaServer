package uni.ami.restdb.model;

import javax.persistence.*;

@Entity
@Table(name = "station")
public class Station extends AuditModel {

    @Id
    @GeneratedValue(generator = "station_generator")
    @SequenceGenerator(
            name = "station_generator",
            sequenceName = "station_sequence",
            initialValue = 10
    )
    private Long id;

    public Long getId() {
        return id;
    }

    @Column(columnDefinition = "text")
    private String name;

    @Column(columnDefinition = "text")
    private String city;

    public String getCity() {
        return city;
    }

    public String getName() {
        return name;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Station{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
