package students.marks.dal.service;

import org.springframework.stereotype.Service;
import students.marks.dal.repository.LabWorkRepository;
import students.marks.dal.repository.MarkRepository;
import students.marks.dal.repository.StudentRepository;
import students.marks.dal.exception.DatabaseException;
import students.marks.dal.model.LabWork;
import students.marks.dal.model.Mark;
import students.marks.dal.model.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
            markRepository.deleteAllByMarkPKLabLabNum(labNum);
        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    @Override
    public LabWork add(LabWork labWork) throws DatabaseException {
        try {
            LabWork addedLab = labWorkRepository.save(labWork);

            List<Student> students = (ArrayList<Student>) studentRepository.findAll();

            for (Student student : students) {

                Mark defaultMark = new Mark();
                defaultMark.setStudent(student);
                defaultMark.setLab(addedLab);

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

    public LabWork findByLabNum(int labNum) throws DatabaseException {
        Optional<LabWork> labWork = labWorkRepository.findById(labNum);

        if (labWork.isEmpty()) {
            throw new DatabaseException("lab work was not found");
        }

        return labWork.get();
    }

}
