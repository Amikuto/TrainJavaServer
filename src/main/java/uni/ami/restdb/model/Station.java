package uni.ami.restdb.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * Сущность станций
 * @author damir
 */
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "station")
public class Station extends AuditModel implements Serializable {

    /**
     * Поле Id сущности
     */
    @Id
    @GeneratedValue(generator = "station_generator")
    @SequenceGenerator(
            name = "station_generator",
            sequenceName = "station_sequence",
            initialValue = 10
    )
    private Long id;

    /**
     * Поле имени станции
     */
    @Column(columnDefinition = "text")
    private String name;

    /**
     * Неопциональное поле внешней связи между станцией и отправляющимеся поездами, которых она в себе содержит
     * представляется в виде списка Train {@link Train}
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "depStation", cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @JsonIgnore
    @ToString.Exclude
    private List<Train> depTrain;

    /**
     * Неопциональное поле внешней связи между станцией и прибывающими поездами, которых она в себе содержит
     * представляется в виде списка Train {@link Train}
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "arrStation", cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @JsonIgnore
    @ToString.Exclude
    private List<Train> arrTrain;

    /**
     * Неопциональное поле внешней связи между станцией и городом, в котором она находится
     * представляется в виде сущности {@link City}
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @JsonBackReference
    @JoinColumn(columnDefinition = "stations")
    @ToString.Exclude
    private City city;

    @Transient
    private String cityName;

    @PostLoad
    private void setCityName() {
        this.cityName = city.getName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Station station = (Station) o;
        return Objects.equals(id, station.id);
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
