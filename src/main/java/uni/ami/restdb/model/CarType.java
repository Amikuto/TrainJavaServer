package uni.ami.restdb.model;

import lombok.Data;

import javax.persistence.*;

/**
 * Сущность типа вагона {@link Car}. Неизменяемая.
 * @author damir
 */
@Entity
@Data
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
}
