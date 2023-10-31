package students.marks.dal.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import students.marks.dal.model.Mark;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface MarkRepository extends CrudRepository<Mark, Long> {

    @Query("SELECT s.name, s.id, m.markPK.lab.labNum, m.value FROM Mark m JOIN Student s ON s.id=m.markPK.student.id")
    List<Object[]> findAllStudentsWithMarksOnOneSubject();

    @Transactional
    void deleteAllByMarkPKLabLabNum(int labNum);

}
