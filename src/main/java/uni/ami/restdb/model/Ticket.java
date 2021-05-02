package uni.ami.restdb.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "ticket")
public class Ticket extends AuditModel {

    @Id
    @GeneratedValue(generator = "ticket_generator")
    @SequenceGenerator(
            name = "ticket_generator",
            sequenceName = "ticket_sequence",
            initialValue = 50
    )
    private Long id;

    @Column(columnDefinition = "int")
    private Integer cost;

    @Column(columnDefinition = "text")
    private String owner;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "ticket")
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @JsonIgnore
    private List<Seat> seats;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @JsonIgnore
    private User user;
}
