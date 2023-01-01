package laniakea.localgroup.milkyway.sol.earth.gr.hua.dit.ds.assigment.AirTours.entities;

import org.springframework.data.annotation.PersistenceConstructor;

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
    @Column(name="user_id")
    private int id;


    @Column(name="username", unique = true)
    @Size(max = 30)
    private String username;

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

    @NotBlank
    @Size(max = 120)
    @Column(name="password")
    private String password;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
                joinColumns = @JoinColumn(name = "user_id"),
                inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.MERGE, CascadeType.PERSIST})
    private List<ActiveTour> activetours;

    @Column(name = "enabled")
    private boolean enabled;

    public User() {}

    public User(String username, String firstName, String lastName, String email, boolean enabled, String encode) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.enabled = enabled;
        this.password = encode;

    }

    // define constructors


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

    public List<ActiveTour> getActiveTours() {
        return activetours;
    }

    public void setActivetours(List<ActiveTour> activetours) {
        this.activetours = activetours;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getPassword() {
        return password;
    }

    // print fields
    public String toString() {
        return "User [id= " + id + ", firstName= " + firstName + ", " +
                "lastName= " + lastName + ", email= " + email + "] - username= " + username;
    }
}
