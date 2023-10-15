package students.marks.db.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import students.marks.model.LabWork;

import javax.transaction.Transactional;

@Repository
public interface LabWorkRepository extends CrudRepository<LabWork, Integer> {

    @Transactional
    void deleteByLabNum(int labNum);

}
