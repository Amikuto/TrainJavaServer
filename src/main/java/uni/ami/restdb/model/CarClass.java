package uni.ami.restdb.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

/**
 * Сущность класса вагона {@link Car}. Неизменяемая.
 * @author damir
 */
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "car_class")
public class CarClass {

    /**
     * Поле Id сущности
     */
    @Id
    private Long id;

    /**
     * Поле названия класса вагона
     */
    @Column(columnDefinition = "text")
    private String name;

//    public CarClass(Long id, String name) {
//        this.id = id;
//        this.name = name;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        CarClass carClass = (CarClass) o;
        return Objects.equals(id, carClass.id);
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
