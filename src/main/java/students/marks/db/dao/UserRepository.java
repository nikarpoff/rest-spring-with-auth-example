package students.marks.db.dao;

import org.springframework.data.repository.CrudRepository;
import students.marks.model.User;

public interface UserRepository extends CrudRepository<User, Long> {

    User findUserByUsername(String username);

}
