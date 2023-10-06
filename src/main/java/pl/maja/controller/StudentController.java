package pl.maja.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.maja.model.Mark;
import pl.maja.model.Student;
import pl.maja.model.Subject;
import pl.maja.service.StudentService;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        return studentService.createStudent(student);
    }

    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudent();
    }

    @GetMapping("/{id}")
    public Optional<Student> getById(@PathVariable int id) {
        return studentService.getStudentById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable int id) {
        studentService.deleteStud(id);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateOnlyPart(@PathVariable int id, @RequestBody Student student) {
        try {
            studentService.updateStudentByID(id, student);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{studentId}/add_subject/{subjectId}")
    public ResponseEntity<Void> addSubjectToStudent(
            @PathVariable int studentId,
            @PathVariable int subjectId
    ) {
        try {
            studentService.addSubjectToStudent(studentId, subjectId);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}/list")
    public ResponseEntity<Set<Subject>> showAllSubjectsOfParticularStudent(@PathVariable int id) {
        Set<Subject> subjects = studentService.showSubjectsAndMarks(id);

        if (subjects != null) {
            return ResponseEntity.ok(subjects);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{studentId}/add_mark/{subjectId}")
    public ResponseEntity<Void> addMarkToStudentSubject(
            @PathVariable int studentId,
            @PathVariable int subjectId,
            @RequestBody Map<String, Integer> requestBody
    ) {
        try {
            Integer value = requestBody.get("value");

            Optional<Student> studentOptional = studentService.getStudentById(studentId);
            if (!studentOptional.isPresent()) {
                return ResponseEntity.notFound().build();
            }
            Student student = studentOptional.get();

            Set<Subject> subjects = student.getSubjects();
            Subject subjectToAddMark = subjects.stream()
                    .filter(subject -> subject.getId() == subjectId)
                    .findFirst()
                    .orElse(null);

            if (subjectToAddMark == null) {
                return ResponseEntity.notFound().build();
            }

            Mark mark = new Mark(value);
            studentService.addMarkToStudentSubject(student, subjectToAddMark, mark);

            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }
}
