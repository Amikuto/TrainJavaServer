package uni.ami.restdb.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "car_class")
public class CarClass {

    @Id
    private Long id;

    @Column(columnDefinition = "text")
    private String name;

//    public Long getId() {
//        return id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    @Override
//    public String toString() {
//        return "CarClass{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                '}';
//    }
}
