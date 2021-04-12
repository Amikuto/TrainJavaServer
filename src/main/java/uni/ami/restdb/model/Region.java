//package uni.ami.restdb.model;
//
//import lombok.Data;
//import org.hibernate.annotations.OnDelete;
//import org.hibernate.annotations.OnDeleteAction;
//
//import javax.persistence.*;
//import java.util.List;
//
//@Data
//@Entity
//@Table(name = "region")
//public class Region {
//
//
//    @Id
//    private Long id;
//
//    @ManyToOne(fetch = FetchType.LAZY, optional = false)
//    @OnDelete(action = OnDeleteAction.NO_ACTION)
//    @JoinColumn(columnDefinition = "region")
//    List<City> cities;
//}
