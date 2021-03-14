package uni.ami.restdb.model;

import javax.persistence.*;

@Entity
@Table(name = "car_type")
public class CarType {

    @Id
    private Long id;

    @Column(columnDefinition = "text")
    private String name;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "CarType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
