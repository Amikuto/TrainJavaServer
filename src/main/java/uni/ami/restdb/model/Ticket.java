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
 * Сущность билетов
 * @author damir
 */
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "ticket")
public class Ticket extends AuditModel {

    /**
     * Поле Id сущности
     */
    @Id
    @GeneratedValue(generator = "ticket_generator")
    @SequenceGenerator(
            name = "ticket_generator",
            sequenceName = "ticket_sequence",
            initialValue = 50
    )
    private Long id;

    /**
     * Поле стоимости билетов
     */
    @Column(columnDefinition = "int")
    private Integer cost;

    /**
     * Поле фио владельца
     */
    @Column(columnDefinition = "text")
    private String owner;

    /**
     * Поле внешней связи между билетом и местом
     * представляется в виде сущности {@link Seat}
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "ticket")
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @JsonIgnore
    @ToString.Exclude
    private List<Seat> seats;

    /**
     * Неопциональное поле внешней связи между билетом и юзером, который купил этот билет
     * представляется в виде сущности {@link User}
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @JsonIgnore
    @ToString.Exclude
    private User user;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Ticket ticket = (Ticket) o;
        return Objects.equals(id, ticket.id);
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
