package uni.ami.restdb.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Objects;

/**
 * Сущность мест (сидений) в базе данных
 * @author damir
 */
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "seat")
public class Seat extends AuditModel {

    /**
     * Поле Id сущности
     */
    @Id
    @GeneratedValue(generator = "seat_generator")
    @SequenceGenerator(
            name = "seat_generator",
            sequenceName = "seat_sequence",
            initialValue = 50
    )
    private Long id;

    /**
     * Поле стоимости сущности места
     */
    @Column(columnDefinition = "int")
    private Integer cost;

    /**
     * Поле номера сущности места в вагоне
     */
    @Column(columnDefinition = "int")
    private Integer number;

    /**
     * Поле внешней связи между сущностью место и его типом (верхнее\нижнее)
     * представляется в виде класса SeatType {@link SeatType}
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @JsonIgnore
    @ToString.Exclude
    private SeatType type;

    /**
     * Поле внешней связи между сущностью место и вагоном, в котором оно находится
     * представляется в виде класса Car {@link Car}
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @JsonBackReference
    @ToString.Exclude
    private Car car;

    /**
     * Опциональное поле внешней связи между сущностью место и билетом
     * представляется в виде класса Ticket {@link Ticket}
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    @ToString.Exclude
    private Ticket ticket;

    @Transient
    private Long cId;

    @Transient
    private String seatType;

    @PostLoad
    private void setCarId() {
        this.cId = car.getId();
        this.seatType = type.getName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Seat seat = (Seat) o;
        return Objects.equals(id, seat.id);
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
