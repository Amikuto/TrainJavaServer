package uni.ami.restdb.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "seat_type")
public class SeatType {

    @Id
    private Long id;

    @Column(columnDefinition = "text")
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "type", cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @JsonIgnore
    private List<Seat> seats;
}
