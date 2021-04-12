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

//@JsonIdentityInfo(
//        generator = ObjectIdGenerators.PropertyGenerator.class,
//        property = "id")
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "station")
public class Station extends AuditModel implements Serializable { //Serializable?

    @Id
    @GeneratedValue(generator = "station_generator")
    @SequenceGenerator(
            name = "station_generator",
            sequenceName = "station_sequence",
            initialValue = 10
    )
    private Long id;

    @Column(columnDefinition = "text", unique = true)
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "depStation", cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
//    @JsonManagedReference
    @JsonIgnore
    private List<Train> depTrain;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "arrStation", cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
//    @JsonManagedReference
    @JsonIgnore
    private List<Train> arrTrain;

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
}
