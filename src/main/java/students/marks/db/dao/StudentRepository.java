package students.marks.db.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import students.marks.model.Student;


@Repository
public interface StudentRepository extends CrudRepository<Student, Long> {

}
