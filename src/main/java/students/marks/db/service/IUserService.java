package students.marks.db.service;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import students.marks.model.User;

public interface IUserService {

    public User loadUserByUsername(String username) throws UsernameNotFoundException;

}
