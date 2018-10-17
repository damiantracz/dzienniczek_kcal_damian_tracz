package damian.kcal.dzienniczek.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="role")
@NoArgsConstructor   //lombok
@AllArgsConstructor  //lombok
@Data
//Adnotacja ta łączy w sobie kilka innych: @Getter, @Setter, @HashCodeAndEquals, @RequiredArgsConstructor oraz @ToString.
public class Role {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="role_id")
    private int id;

    @Column(name="role")
    private String role;

/*    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }*/
}