package uni.ami.restdb.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * Сущность пользователей
 * @author damir
 */
@Data
@Entity
@Table(name="\"user\"")
public class User {

    /**
     * Поле Id сущности
     */
    @Id
    @GeneratedValue(generator = "user_generator")
    @SequenceGenerator(
            name = "user_generator",
            sequenceName = "user_sequence",
            initialValue = 50
    )
    private Long id;

    /**
     * Поле фио пользователя
     */
    @Column(columnDefinition = "text", unique = true)
    private String fullName;

    /**
     * Поле почты пользователя, уникально
     */
    @Column(columnDefinition = "text", unique = true)
    private String email;

    /**
     * Поле логина пользователя, уникально
     */
    @Column(columnDefinition = "text", unique = true)
    private String login;

    /**
     * Поле пароля пользователя
     */
    @Column(columnDefinition = "text")
    private String password;

    /**
     * Неопциональное поле внешней связи между пользователем и билетами, которые он купил
     * представляется в виде сущности {@link Ticket}
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private List<Ticket> tickets;
}
