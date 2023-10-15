package students.marks.db.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import students.marks.model.Mark;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface MarkRepository extends CrudRepository<Mark, Long> {

    @Query("SELECT s.name, s.id, m.markPK.labNum, m.value FROM Mark m JOIN Student s ON s.id=m.markPK.studentId")
    List<Object[]> findAllStudentsWithMarksOnOneSubject();

    @Transactional
    void deleteAllByMarkPKLabNum(int labNum);

}
