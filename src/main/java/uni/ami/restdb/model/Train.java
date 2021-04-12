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

//@JsonIdentityInfo(
//        generator = ObjectIdGenerators.PropertyGenerator.class,
//        property = "id")
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "train")
public class Train extends AuditModel {

    @Id
    @GeneratedValue(generator = "train_generator")
    @SequenceGenerator(
            name = "train_generator",
            sequenceName = "train_sequence",
            initialValue = 10
    )
    private Long id;

    @Column(columnDefinition = "date")
    private LocalDate dateDep;

    @Column(columnDefinition = "date")
    private LocalDate dateArr;

    @Column(columnDefinition = "time")
    private LocalTime timeDep;

    @Column(columnDefinition = "time")
    private LocalTime timeArr;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @JsonIgnore
//    @JsonBackReference
    @JoinColumn(columnDefinition = "depTrain")
    private Station depStation;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @JsonIgnore
//    @JsonBackReference
    @JoinColumn(columnDefinition = "arrTrain")
    private Station arrStation;

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
}
