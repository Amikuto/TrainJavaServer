package uni.ami.restdb.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;

/**
 * Сущность вагонов базы данных
 * @author damir
 */
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "car")
public class Car extends AuditModel {

    /**
     * Поле id сущности
     */
    @Id
    @GeneratedValue(generator = "car_generator")
    @SequenceGenerator(
            name = "car_generator",
            sequenceName = "car_sequence",
            initialValue = 10
    )
    private Long id;

    /**
     * Поле номера вагона в поезде
     * представляется в виде типа int PostgreSQL
     */
    @Column(columnDefinition = "int")
    private Integer number;

    /**
     * Неопциональное поле внешней связи между вагоном и классом вагона
     * представляется в виде класса CarClass {@link CarClass}
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "car_class")
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @JsonIgnore
    private CarClass carClass;

    /**
     * Неопциональное поле внешней связи между вагоном и типом вагона
     * представляется в виде класса CarType {@link CarType}
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "car_type")
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @JsonIgnore
    private CarType carType;

    /**
     * Неопциональное поле внешней связи между вагоном и поездом
     * представляется в виде класса Train {@link Train}
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "train_id")
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @JsonIgnore
    private Train train;

    /**
     * Неопциональное поле внешней связи между вагоном и местами, которые он в себе содержит
     * представляется в виде списка Seat {@link Seat}
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "car")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonManagedReference
    private List<Seat> seats;

    /**
     * Поле класса Car для отображения Id класса
     * необходимо для общения в json
     */
    @Transient
    private String cClass;

    /**
     * Поле класса Car для отображения Id типа
     * необходимо для общения в json
     */
    @Transient
    private String cType;

    /**
     * Поле класса Car для отображения Id поезда
     * необходимо для общения в json
     */
    @Transient
    private Long tId;

    /**
     * Метод для заполнения полей класса, типа, поезда
     */
    @PostLoad
    private void setTrainClassType() {
        this.cClass = carClass.getName();
        this.cType = carType.getName();
        this.tId = train.getId();
    }

    public Car(Long id, Integer number) {
        this.id = id;
        this.number = number;
    }
}
