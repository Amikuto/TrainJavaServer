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
 * Сущность пользователей
 * @author damir
 */
@Getter
@Setter
@ToString
@RequiredArgsConstructor
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
    @ToString.Exclude
    private List<Ticket> tickets;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
