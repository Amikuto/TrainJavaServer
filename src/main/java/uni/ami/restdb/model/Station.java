package uni.ami.restdb.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.context.annotation.Primary;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

//@JsonIdentityInfo(
//        generator = ObjectIdGenerators.PropertyGenerator.class,
//        property = "id")
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "station")
public class Station extends AuditModel implements Serializable { //Serializable?

    @Id
    @GeneratedValue(generator = "station_generator")
    @SequenceGenerator(
            name = "station_generator",
            sequenceName = "station_sequence",
            initialValue = 10
    )
    private Long id;

    @Column(columnDefinition = "text", unique = true)
    private String name;

    @Column(columnDefinition = "text")
    private String city;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "depStation", cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
//    @JsonManagedReference
    @JsonIgnore
    private List<Train> depTrain;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "arrStation", cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
//    @JsonManagedReference
    @JsonIgnore
    private List<Train> arrTrain;

//    public String getCity() {
//        return city;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    @JsonManagedReference
//    public List<Train> getDepTrain() {
//        return depTrain;
//    }
//
//    @JsonManagedReference
//    public List<Train> getArrTrain() {
//        return arrTrain;
//    }
//
//    public void setCity(String city) {
//        this.city = city;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public void setDepTrain(List<Train> depTrain) {
//        this.depTrain = depTrain;
//    }
//
//    public void setArrTrain(List<Train> arrTrain) {
//        this.arrTrain = arrTrain;
//    }

//    @Override
//    public String toString() {
//        return "Station{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", city='" + city + '\'' +
//                '}';
//    }
}
