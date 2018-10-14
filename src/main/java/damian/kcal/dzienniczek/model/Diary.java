package damian.kcal.dzienniczek.model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "Diary")
public class Diary {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int diaryId;

    @CreationTimestamp
    private Date date;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User userId;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product productId;

    @NotNull
    private float productWeight;

}
