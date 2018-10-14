package damian.kcal.dzienniczek.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "Product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int productId;

    @Size(max = 256)
    @NotNull
    private String productName;

    @NotNull
    private float productCarbohydrate;

    @NotNull
    private float productProtein;

    @NotNull
    private float productFat;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User userId;

}
