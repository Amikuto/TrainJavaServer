package uni.ami.restdb.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "seat")
public class Seat extends AuditModel {

    @Id
    @GeneratedValue(generator = "seat_generator")
    @SequenceGenerator(
            name = "seat_generator",
            sequenceName = "seat_sequence",
            initialValue = 50
    )
    private Long id;

    @Column(columnDefinition = "text")
    private String type; // Upper or lower

    @Column(columnDefinition = "int")
    private Integer cost;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
//    @JoinColumn(name = "carSeats")
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @JsonIgnore
    private Car car;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
//    @JoinColumn(name = "ticketId")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Ticket ticket;

//    public String getType() {
//        return type;
//    }
//
//    public Integer getCost() {
//        return cost;
//    }
//
//    public Car getCar() {
//        return car;
//    }
//
//    public Ticket getTicket() {
//        return ticket;
//    }
//
//    public void setType(String type) {
//        this.type = type;
//    }
//
//    public void setCost(Integer cost) {
//        this.cost = cost;
//    }
//
//    public void setCar(Car car) {
//        this.car = car;
//    }
//
//    public void setTicket(Ticket ticket) {
//        this.ticket = ticket;
//    }
//
//    @Override
//    public String toString() {
//        return "Seat{" +
//                "id=" + id +
//                ", type='" + type + '\'' +
//                ", cost=" + cost +
//                ", car=" + car +
//                ", ticket=" + ticket +
//                '}';
//    }
}
