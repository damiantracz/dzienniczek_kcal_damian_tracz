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
    private Integer userId;

//    @Size(min = 6, max = 20)
    @NotNull
    @Column(unique = true)
    private String userName;

    @Size(max = 256)
    @NotNull
    @Column(unique = true)
    private String userEmail;

    @NotNull
    @Size(min = 7)
    private String userPassword;

    @Transient   //nie tworzy pola w bazie danych
    private String userPassword2;

    @CreationTimestamp
    private Date userCreatedDate;

    @Column(columnDefinition = "float default '100'")
    private float userCarbohydrate;

    @Column(columnDefinition = "float default '100'")
    private float userProtein;

    @Column(columnDefinition = "float default '30'")
    private float userFat;



    //gettery, settery
    public int getId(){
        return userId;
    }

    public void setId(int userId) {
        this.userId = userId;
    }



    public String getUserName(){
        return userName;
    }

    public void setUserName(String userName){
        this.userName = userName;
    }


    public String getUserEmail(){
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }


    public String getUserPassword(){
        return userPassword;
    }

    public void setUserPassword(String userPassword){
        this.userPassword = userPassword;
    }

    public String getUserPassword2() {
        return userPassword2;
    }

    public void setUserPassword2(String userPassword2) {
        this.userPassword2 = userPassword2;
    }

    public Date getUserCreatedDate(){
        return userCreatedDate;
    }

    public void setUserCreatedDate(Date date){
        this.userCreatedDate = date;
    }




    public float getUserCarbohydrate(){
        return userCarbohydrate;
    }


    public void setUserCarbohydrate(float userCarbohydrate){
        this.userCarbohydrate = userCarbohydrate;
    }



    public float getUserProtein(){
        return userProtein;
    }

    public void setUserProtein(float userProtein){
        this.userProtein = userProtein;
    }


    public float getUserFat(){
        return userFat;
    }

    public void setUserFat(float userFat){
        this.userFat = userFat;
    }



}
