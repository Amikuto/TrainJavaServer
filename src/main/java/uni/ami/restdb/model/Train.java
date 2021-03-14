package uni.ami.restdb.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "train")
public class Train extends AuditModel{

    @Id
    @GeneratedValue(generator = "train_generator")
    @SequenceGenerator(
            name = "train_generator",
            sequenceName = "train_sequence",
            initialValue = 10
    )
    private Long id;

    public Long getId() {
        return id;
    }

    @Column(columnDefinition = "time")
    private LocalTime time_arr;

    @Column(columnDefinition = "time")
    private LocalTime time_dep;

    @Column(columnDefinition = "date")
    private LocalDate date_arr;

    @Column(columnDefinition = "date")
    private LocalDate date_dep;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "station_arr_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Station arrStation;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "station_dep_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Station depStation;

    public Station getStation() {
        return arrStation;
    }

    public Station getStation_dep() {
        return depStation;
    }

    public LocalTime getTime_arr() {
        return time_arr;
    }

    public LocalTime getTime_dep() {
        return time_dep;
    }

    public LocalDate getDate_arr() {
        return date_arr;
    }

    public LocalDate getDate_dep() {
        return date_dep;
    }

    public void setStation(Station station_arr) {
        this.arrStation = station_arr;
    }

    public void setStation_dep(Station station_dep) {
        this.depStation = station_dep;
    }

    public void setTime_arr(LocalTime time_arr) {
        this.time_arr = time_arr;
    }

    public void setTime_dep(LocalTime time_dep) {
        this.time_dep = time_dep;
    }

    public void setDate_arr(LocalDate date_arr) {
        this.date_arr = date_arr;
    }

    public void setDate_dep(LocalDate date_dep) {
        this.date_dep = date_dep;
    }

    @Override
    public String toString() {
        return "Train{" +
                "id=" + id +
                ", time_arr=" + time_arr +
                ", time_dep=" + time_dep +
                ", date_arr=" + date_arr +
                ", date_dep=" + date_dep +
                ", station_arr=" + arrStation +
                ", station_dep=" + depStation +
                '}';
    }
}
