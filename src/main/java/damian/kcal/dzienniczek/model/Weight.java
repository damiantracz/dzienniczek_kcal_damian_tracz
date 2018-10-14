package damian.kcal.dzienniczek.model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Weight")
public class Weight {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int weightId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User userId;

    @CreationTimestamp
    private Date weightDate;
}
