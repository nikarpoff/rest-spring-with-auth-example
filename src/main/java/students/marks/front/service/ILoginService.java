package students.marks.front.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface ILoginService {

    public UserDetails loadUserByUsername(String string) throws UsernameNotFoundException;

}
