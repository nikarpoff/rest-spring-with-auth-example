package students.marks.dal.service;

import students.marks.dal.repository.LabWorkRepository;
import students.marks.dal.repository.MarkRepository;
import students.marks.dal.repository.StudentRepository;
import students.marks.dal.exception.DatabaseException;
import students.marks.dal.model.LabWork;
import students.marks.dal.model.Mark;
import students.marks.dal.model.Student;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService implements IService<Student> {
    private final StudentRepository studentRepository;
    private final MarkRepository markRepository;
    private final LabWorkRepository labWorkRepository;

    public StudentService(StudentRepository repository, MarkRepository markRepository, LabWorkRepository labWorkRepository) {
        this.studentRepository = repository;
        this.markRepository = markRepository;
        this.labWorkRepository = labWorkRepository;
    }

    @Override
    public Iterable<Student> listAll() {
        return studentRepository.findAll();
    }

    @Override
    public void delete(Long studentId) throws DatabaseException {
        try {
            studentRepository.deleteById(studentId);
        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    @Override
    public Student add(Student student) throws DatabaseException {
        try {
            List<LabWork> labWorkList = (ArrayList<LabWork>) labWorkRepository.findAll();

            Student addedStudent = studentRepository.save(student);

            for (LabWork labWork : labWorkList) {

                Mark defaultMark = new Mark();
                defaultMark.setStudent(addedStudent);
                defaultMark.setLab(labWork);

                markRepository.save(defaultMark);
            }

            return addedStudent;
        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    @Override
    public Student update(Student student) throws DatabaseException {
        return null;
    }

    @Override
    public Student findById(Long id) throws DatabaseException {
        Optional<Student> student = studentRepository.findById(id);

        if (student.isEmpty()) {
            throw new DatabaseException("Student not found");
        }

        return student.get();
    }
}
