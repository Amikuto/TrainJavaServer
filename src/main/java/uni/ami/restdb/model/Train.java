package uni.ami.restdb.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;

/**
 * Сущность поездов
 * @author damir
 */
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "train")
public class Train extends AuditModel {

    /**
     * Поле Id сущности
     */
    @Id
    @GeneratedValue(generator = "train_generator")
    @SequenceGenerator(
            name = "train_generator",
            sequenceName = "train_sequence",
            initialValue = 10
    )
    private Long id;

    /**
     * Поле даты отправления поезда в формате LocalDate
     */
    @Column(columnDefinition = "date")
    private LocalDate dateDep;

    /**
     * Поле даты прибытия поезда
     */
    @Column(columnDefinition = "date")
    private LocalDate dateArr;

    /**
     * Поле времени отправления поезда
     */
    @Column(columnDefinition = "time")
    private LocalTime timeDep;

    /**
     * Поле времени прибытия поезда
     */
    @Column(columnDefinition = "time")
    private LocalTime timeArr;

    /**
     * Неопциональное поле внешней связи между поездом и станцией отправления
     * представляется в виде сущности {@link Station}
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @JsonIgnore
    @JoinColumn(columnDefinition = "depTrain")
    @ToString.Exclude
    private Station depStation;

    /**
     * Неопциональное поле внешней связи между поездом и станцией прибытия
     * представляется в виде сущности {@link Station}
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @JsonIgnore
    @JoinColumn(columnDefinition = "arrTrain")
    @ToString.Exclude
    private Station arrStation;

    /**
     * Неопциональное поле внешней связи между поездом и вагонами, которые он в себе содержит
     * представляется в виде списка {@link Car}
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "train")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    @ToString.Exclude
    private List<Car> cars;

    @Transient // Чтобы строка не создавалась в бд
    private String arrSt;

    @Transient
    private String depSt;

    @Transient
    private String arrivalCity;

    @Transient
    private String departingCity;

    @PostLoad
    private void setArrAndDepSt() {
        this.depSt = depStation.getName();
        this.arrSt = arrStation.getName();
        this.departingCity = depStation.getCityName();
        this.arrivalCity = arrStation.getCityName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Train train = (Train) o;
        return Objects.equals(id, train.id);
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
