package uni.ami.restdb.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "car")
public class Car extends AuditModel {

    @Id
    @GeneratedValue(generator = "car_generator")
    @SequenceGenerator(
            name = "car_generator",
            sequenceName = "car_sequence",
            initialValue = 10
    )
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "car_class")
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @JsonIgnore
    private CarClass carClass;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "car_type")
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @JsonIgnore
    private CarType carType;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "train_id")
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @JsonIgnore
    private Train train;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "car")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private List<Seat> seats;

    @Transient
    private Long cClass;

    @Transient
    private Long cType;

    @Transient
    private Long tId;

//    public Long getId() {
//        return id;
//    }
//
//    public CarClass getCarClass() {
//        return carClass;
//    }
//
//    public CarType getCarType() {
//        return carType;
//    }
//
//    public Train getTrain() {
//        return train;
//    }
//
//    public void setCarClass(CarClass carClass) {
//        this.carClass = carClass;
//    }
//
//    public void setCarType(CarType carType) {
//        this.carType = carType;
//    }
//
//    public void setTrain(Train train) {
//        this.train = train;
//    }
//
//    @Override
//    public String toString() {
//        return "Car{" +
//                "id=" + id +
//                ", carClass=" + carClass +
//                ", carType=" + carType +
//                ", train=" + train +
//                '}';
//    }
}
