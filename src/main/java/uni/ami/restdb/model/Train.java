package uni.ami.restdb.model;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * Сущность поездов
 * @author damir
 */
@EqualsAndHashCode(callSuper = true)
@Data
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
    private Station depStation;

    /**
     * Неопциональное поле внешней связи между поездом и станцией прибытия
     * представляется в виде сущности {@link Station}
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @JsonIgnore
    @JoinColumn(columnDefinition = "arrTrain")
    private Station arrStation;

    /**
     * Неопциональное поле внешней связи между поездом и вагонами, которые он в себе содержит
     * представляется в виде списка {@link Car}
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "train")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
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

    public Train(Long id, LocalDate dateDep, LocalDate dateArr, String departingCity, String arrivalCity) {
        this.id = id;
        this.dateDep = dateDep;
        this.dateArr = dateArr;
        this.departingCity = departingCity;
        this.arrivalCity = arrivalCity;
    }
}
