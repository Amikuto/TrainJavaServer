package uni.ami.restdb.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;

/**
 * Сущность городов в базе данных
 * @author damir
 */
@Data
@Entity
@Table(name = "city")
public class City {

    /**
     * Поле Id сущности
     */
    @Id
    @GeneratedValue(generator = "city_generator")
    @SequenceGenerator(
            name = "city_generator",
            sequenceName = "city_sequence",
            initialValue = 10
    )
    private Long id;

    /**
     * Поле имени города
     */
    @Column(columnDefinition = "text", unique = true)
    private String name;

    /**
     * Поле внешней связи между сущностью город и станциями, которые он в себе содержит.
     * представляется в виде списка станций {@link Station}
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "city", cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @JsonManagedReference
    private List<Station> stations;

    public City(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
