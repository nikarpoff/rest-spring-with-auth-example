package students.marks.model;


import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "USR")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID", nullable = false, updatable = false)
    private Long userId;

    @Column(name = "USERNAME", nullable = false)
    private String username;

    @Column(name = "PASS_HASH", nullable = false)
    private String passHash;

    @Column(name = "ACTIVE")
    private boolean active;

    @Column(name = "ROLES")
    private String roles;

    public User(Long id, String username, String passHash) {
        this.userId = id;
        this.username = username;
        this.passHash = passHash;
    }

    public User() {

    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long id) {
        this.userId = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String login) {
        this.username = login;
    }

    public String getPassHash() {
        return passHash;
    }

    public void setPassHash(String passHash) {
        this.passHash = passHash;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userId, user.userId) && Objects.equals(username, user.username) && Objects.equals(passHash, user.passHash);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, username, passHash);
    }
}
