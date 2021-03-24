package uni.ami.restdb.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
@Entity
@Table(name="\"user\"")
public class User {

    @Id
    @GeneratedValue(generator = "user_generator")
    @SequenceGenerator(
            name = "user_generator",
            sequenceName = "user_sequence",
            initialValue = 50
    )
    private Long id;

    @Column(columnDefinition = "text")
    private String fullName;

    @Column(columnDefinition = "text")
    private String email;

    @Column(columnDefinition = "text")
    private String login;

    @Column(columnDefinition = "text")
    private String password;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private List<Ticket> tickets;
}
