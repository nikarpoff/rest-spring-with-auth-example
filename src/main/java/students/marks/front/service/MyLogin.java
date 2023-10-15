package students.marks.front.service;

import org.springframework.security.core.authority.AuthorityUtils;
import students.marks.model.User;

public class MyLogin extends org.springframework.security.core.userdetails.User {

    private static final long serialVersionUID = 1L;

    private User user;

    public MyLogin(User user) {
        super(user.getUsername(), user.getPassHash(), AuthorityUtils.createAuthorityList("ALL"));
        this.user = user;
    }

}
