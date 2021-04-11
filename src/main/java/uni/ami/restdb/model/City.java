package uni.ami.restdb.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "city")
public class City {

    @Id
    @GeneratedValue(generator = "city_generator")
    @SequenceGenerator(
            name = "city_generator",
            sequenceName = "city_sequence",
            initialValue = 10
    )
    private Long id;

    @Column(columnDefinition = "text")
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "city", cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @JsonManagedReference
    private List<Station> stations;

//    public Long getId() {
//        return id;
//    }



}
