package damian.kcal.dzienniczek.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "product")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "name")
    private String name;

    @CreationTimestamp
    @Column(name = "date")
    private Date date;

    @Column(name = "carbohydrates")
    private double carbohydrates;

    @Column(name = "protein")
    private double protein;

    @Column(name = "fat")
    private double fat;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;


//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "user_id")
//    private User user;

}
