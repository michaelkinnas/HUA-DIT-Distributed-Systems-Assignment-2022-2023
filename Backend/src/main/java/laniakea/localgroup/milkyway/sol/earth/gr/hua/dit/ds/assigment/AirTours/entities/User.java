package laniakea.localgroup.milkyway.sol.earth.gr.hua.dit.ds.assigment.AirTours.entities;



import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="users")
public class User {

    // define fields
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="first_name")
    @NotBlank(message="Please provide a first name.")
    @Size(max =50, message= "Name should not be greater than 50 characters.")
    private String firstName;

    @Column(name="last_name")
    @NotBlank(message="Please provide a last name.")
    @Size(max =50, message= "Name should not be greater than 50 characters.")
    private String lastName;

    @Column(unique = true)
    @Email(message = "Please provide a valid email.")  // It checks the email format, to be valid.
    @Size(max = 50)
    private String email;

    @NotBlank(message = "Please provide a password.")
    private String password;

    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Role> roles = new HashSet<>();

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }


    // define constructors
    public User() {}

    public User(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    // define getters-setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getPassword() {
        return password;
    }

    public void  setPassword() {
        this.password = password;
    }

    // print fields
    public String toString() {
        return "User [id= " + id + ", firstName= " + firstName + ",lastName= " + lastName + "," +
                " email= " + email + "]";
    }
}
