package uni.ami.restdb.model;

import lombok.Data;

import javax.persistence.*;

/**
 * Сущность класса вагона {@link Car}. Неизменяемая.
 * @author damir
 */
@Data
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

    public CarClass(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
