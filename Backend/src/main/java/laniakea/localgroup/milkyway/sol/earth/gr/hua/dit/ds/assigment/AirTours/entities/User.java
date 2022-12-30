package laniakea.localgroup.milkyway.sol.earth.gr.hua.dit.ds.assigment.AirTours.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name="users")
public class User {

    // define fields
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="user_id")
    private int id;

    @Column(name="first_name")
    @NotBlank(message="Please enter the first name")
    @Size(max =30, message= "Name should not be greater than 30 characters")
    private String firstName;

    @Column(name="last_name")
    @NotBlank(message="Please enter the last name")
    @Size(max =30, message= "Name should not be greater than 30 characters")
    private String lastName;

    @Column(name="email", unique = true)
    @Email(message = "Please enter a valid email")  // It checks the email format, to be valid.
    @Size(max = 50)
    private String email;

    @Column(name="username", unique = true)
    @Size(max = 30)
    private String username;

    @Column(name="password")
    private String password;

    // define constructors
    public User() {}

    public User(String firstName, String lastName, String email, String username, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    // define getters-setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    // print fields
    public String toString() {
        return "User [id= " + id + ", firstName= " + firstName + ", " +
                "lastName= " + lastName + ", email= " + email + "] - username= " + username;
    }
}
