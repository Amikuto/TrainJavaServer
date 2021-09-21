package uni.ami.restdb.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

/**
 * Сущность типа вагона {@link Car}. Неизменяемая.
 * @author damir
 */
@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "car_type")
public class CarType {

    /**
     * Поле Id сущности
     */
    @Id
    private Long id;

    /**
     * Поле названия типа вагона
     */
    @Column(columnDefinition = "text")
    private String name;

    public CarType(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        CarType carType = (CarType) o;
        return Objects.equals(id, carType.id);
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
