package uni.ami.restdb.model;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
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

//    public Long getId() {
//        return id;
//    }

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
//    @JoinColumn(name = "dep_staion_id")
    @JsonIgnore
//    @JsonBackReference
    @JoinColumn(columnDefinition = "depTrain")
    private Station depStation;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
//    @JoinColumn(name = "arr_staion_id")
    @JsonIgnore
//    @JsonBackReference
    @JoinColumn(columnDefinition = "arrTrain")
    private Station arrStation;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "train")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private List<Car> cars;

    @Transient // Чтобы строка не создавалась в бд
    private Long arrSt;

    @Transient
    private Long depSt;

//    public List<Car> getCars() {
//        return cars;
//    }
//
//    public LocalTime getTime_arr() {
//        return time_arr;
//    }
//
//    public LocalTime getTime_dep() {
//        return time_dep;
//    }
//
//    public LocalDate getDate_arr() {
//        return date_arr;
//    }
//
//    public LocalDate getDate_dep() {
//        return date_dep;
//    }
//
//    @JsonBackReference
//    public Station getArrStation() {
//        return arrStation;
//    }
//
//    @JsonBackReference
//    public Station getDepStation() {
//        return depStation;
//    }
//
//    public Long getArrSt() {
//        return arrSt;
//    }
//
//    public Long getDepSt() {
//        return depSt;
//    }
//
//    public void setTime_arr(LocalTime time_arr) {
//        this.time_arr = time_arr;
//    }
//
//    public void setTime_dep(LocalTime time_dep) {
//        this.time_dep = time_dep;
//    }
//
//    public void setDate_arr(LocalDate date_arr) {
//        this.date_arr = date_arr;
//    }
//
//    public void setDate_dep(LocalDate date_dep) {
//        this.date_dep = date_dep;
//    }
//
//    public void setArrStation(Station arrStation) {
//        this.arrStation = arrStation;
//    }
//
//    public void setDepStation(Station depStation) {
//        this.depStation = depStation;
//    }
//
//    public void setCars(List<Car> cars) {
//        this.cars = cars;
//    }
//
//    public void setDepSt(Long depSt) {
//        this.depSt = depSt;
//    }
//
//    public void setArrSt(Long arrSt) {
//        this.arrSt = arrSt;
//    }

//    @Override
//    public String toString() {
//        return "Train{" +
//                "id=" + id +
//                ", time_arr=" + time_arr +
//                ", time_dep=" + time_dep +
//                ", date_arr=" + date_arr +
//                ", date_dep=" + date_dep +
////                ", arrStation=" + arrStation +
////                ", depStation=" + depStation +
////                ", cars=" + cars +
//                ", arrSt=" + arrSt +
//                ", depSt=" + depSt +
//                '}';
//    }

    //    @Override
//    public String toString() {
//        return "Train{" +
//                "id=" + id +
//                ", time_arr=" + time_arr +
//                ", time_dep=" + time_dep +
//                ", date_arr=" + date_arr +
//                ", date_dep=" + date_dep +
//                ", arrStation=" + arrStation +
//                ", depStation=" + depStation +
//                ", arrSt=" + arrSt +
//                ", depSt=" + depSt +
//                '}';
//    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof Station )) return false;
//        return id != null && id.equals(((Station) o).getId());
//    }
//
//    @Override
//    public int hashCode() {
//        return getClass().hashCode();
//    }
}
