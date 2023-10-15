package students.marks.db.service;

import org.springframework.stereotype.Service;
import students.marks.db.dao.LabWorkRepository;
import students.marks.db.dao.MarkRepository;
import students.marks.db.dao.StudentRepository;
import students.marks.db.exception.DatabaseException;
import students.marks.model.LabWork;
import students.marks.model.Mark;
import students.marks.model.Student;

import java.util.ArrayList;
import java.util.List;

@Service
public class LabWorkService implements IService<LabWork> {
    private final StudentRepository studentRepository;
    private final MarkRepository markRepository;
    private final LabWorkRepository labWorkRepository;

    public LabWorkService(StudentRepository studentRepository, MarkRepository markRepository, LabWorkRepository labWorkRepository) {
        this.studentRepository = studentRepository;
        this.markRepository = markRepository;
        this.labWorkRepository = labWorkRepository;
    }

    @Override
    public Iterable<LabWork> listAll() {
        return labWorkRepository.findAll();
    }

    @Override
    public void delete(Long id) throws DatabaseException {

    }

    public void deleteByLabNum(int labNum) throws DatabaseException {
        try {
            labWorkRepository.deleteByLabNum(labNum);
            markRepository.deleteAllByMarkPKLabNum(labNum);
        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    @Override
    public LabWork add(LabWork labWork) throws DatabaseException {
        try {
            LabWork addedLab = labWorkRepository.save(labWork);

            List<Student> students = (ArrayList<Student>) studentRepository.findAll();
            int newLabNum = addedLab.getLabNum();

            for (Student student : students) {

                Mark defaultMark = new Mark();
                defaultMark.setStudentId(student.getId());
                defaultMark.setLabNum(newLabNum);

                markRepository.save(defaultMark);
            }

            return addedLab;
        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    @Override
    public LabWork update(LabWork labWork) throws DatabaseException {
        return null;
    }

    @Override
    public LabWork findById(Long id) throws DatabaseException {
        return null;
    }

}
