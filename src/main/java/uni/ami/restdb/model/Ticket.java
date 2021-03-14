package uni.ami.restdb.model;

import javax.persistence.*;

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

    public Integer getCost() {
        return cost;
    }

    public String getOwner() {
        return owner;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", cost=" + cost +
                ", owner='" + owner + '\'' +
                '}';
    }
}
