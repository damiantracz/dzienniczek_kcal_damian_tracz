package damian.kcal.dzienniczek.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "user_weight")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Weight {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @CreationTimestamp
    @Column(name = "date")
    private Date date;

    @Column(name = "weight")
    private float weight;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;
}
