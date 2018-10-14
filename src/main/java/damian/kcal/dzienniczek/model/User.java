package damian.kcal.dzienniczek.model;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@EnableAutoConfiguration
@Table(name = "User")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int userId;

    @Size(min = 6, max = 20)
    @NotNull
    @Column(unique = true)
    private String userName;

    @Size(max = 256)
    @NotNull
    @Column(unique = true)
    private String userEmail;

    @NotNull
    @Size(min = 7, max = 25)
    private String userPassword;

    @CreationTimestamp
    private Date userCreatedDate;

    @Column(columnDefinition = "float default '100'")
    private float userCarbohydrate;

    @Column(columnDefinition = "float default '100'")
    private float userProtein;

    @Column(columnDefinition = "float default '30'")
    private float userFat;

    @ManyToOne
    @JoinColumn(name = "role_name")
    private Role roleName;
}
