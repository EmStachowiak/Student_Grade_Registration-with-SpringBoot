package pl.maja.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import pl.maja.model.Mark;
import pl.maja.model.Professor;
import pl.maja.model.Student;
import pl.maja.model.Subject;
import pl.maja.repository.MarkRepository;
import pl.maja.repository.StudentRepository;
import pl.maja.repository.SubjectRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class StudentService {

    private StudentRepository studentRepository;
    private SubjectRepository subjectRepository;
    private MarkRepository markRepository;

    public StudentService(StudentRepository studentRepository, SubjectRepository subjectRepository, MarkRepository markRepository) {
        this.studentRepository = studentRepository;
        this.subjectRepository = subjectRepository;
        this.markRepository = markRepository;
    }

    public Student createStudent(Student student) {
        return  studentRepository.save(student);
    }

    public List<Student> getAllStudent() {
        return studentRepository.findAll();
    }

    public Optional<Student> getStudentById(int id) {
        return studentRepository.findById(id);
    }

    public void deleteStud(int id) {
        studentRepository.deleteById(id);
    }

    public void updateStudentByID(int id, Student updatedStudent) {
        Optional<Student> existingStudent = studentRepository.findById(id);

        if(existingStudent.isPresent()) {
            Student student = existingStudent.get();

            if (updatedStudent.getName() != null) {
                student.setName(updatedStudent.getName());
            }
            if (updatedStudent.getSurname() != null) {
                student.setSurname(updatedStudent.getSurname());
            }
            if (updatedStudent.getSchoolClass() != null) {
                student.setSchoolClass(updatedStudent.getSchoolClass());
            }
            studentRepository.save(student);
        } else {
            System.out.println("There is no student with this name.");
        }
    }

    public void addSubjectToStudent(int studentId, int subjectId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new EntityNotFoundException("Nie znaleziono studenta o podanym ID"));
        Subject subject = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new EntityNotFoundException("Nie znaleziono przedmiotu o podanym ID"));

        student.addSubject(subject);
        studentRepository.save(student);
    }

    public Set<Subject> showSubjectsAndMarks(int studentId) {
        Optional<Student> student = studentRepository.findById(studentId);
        Set<Subject> subjects = null;

        if (student != null) {
            Student st = student.get();
            subjects = st.getSubjects();
        }
        return subjects;
    }

    public void addMarkToStudentSubject(Student student, Subject subject, Mark mark) {
        subject.getMarks().add(mark);
        mark.setSubject(subject);
        studentRepository.save(student);
        markRepository.save(mark);

    }
}
