package uni.ami.restdb.model;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.context.annotation.Primary;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Сущность станций
 * @author damir
 */
@EqualsAndHashCode(callSuper = true)
@Data
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
    @Column(columnDefinition = "text", unique = false)
    private String name;

    /**
     * Неопциональное поле внешней связи между станцией и отправляющимеся поездами, которых она в себе содержит
     * представляется в виде списка Train {@link Train}
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "depStation", cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @JsonIgnore
    private List<Train> depTrain;

    /**
     * Неопциональное поле внешней связи между станцией и прибывающими поездами, которых она в себе содержит
     * представляется в виде списка Train {@link Train}
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "arrStation", cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @JsonIgnore
    private List<Train> arrTrain;

    /**
     * Неопциональное поле внешней связи между станцией и городом, в котором она находится
     * представляется в виде сущности {@link City}
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @JsonBackReference
    @JoinColumn(columnDefinition = "stations")
    private City city;

    @Transient
    private String cityName;

    @PostLoad
    private void setCityName() {
        this.cityName = city.getName();
    }

    public Station(Long id, String name, City city) {
        this.id = id;
        this.name = name;
        this.city = city;
    }
}
