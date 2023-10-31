package students.marks.dal.model;


import lombok.Data;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "USR")
@Data
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

}
