package uni.ami.restdb.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

/**
 * Сущность типа места (верхнее\нижнее)
 * @author damir
 */
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "seat_type")
public class SeatType {

    /**
     * Поле Id сущности
     */
    @Id
    private Long id;

    /**
     * Поле имени сущности
     */
    @Column(columnDefinition = "text")
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "type", cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @JsonIgnore
    @ToString.Exclude
    private List<Seat> seats;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        SeatType seatType = (SeatType) o;
        return Objects.equals(id, seatType.id);
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
