package pl.maja.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.maja.model.Subject;
import pl.maja.service.SubjectService;

import java.util.List;

@RestController
@RequestMapping("/subjects")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;


    @PostMapping
    public Subject createSub(@RequestBody Subject subject) {
        return subjectService.createSubject(subject);
    }

    @GetMapping
    public List<Subject> listSubjects() {
        return subjectService.getAllSubjects();
    }


    @PatchMapping("/{subjectId}/add_professor/{professorId}")
    public ResponseEntity<Void> addProfessorToSubject(
           @PathVariable int subjectId,
           @PathVariable int professorId
        ) {
           try {
                subjectService.addProfToSubject(subjectId, professorId);
                return ResponseEntity.noContent().build();
            } catch (Exception e) {
                return ResponseEntity.notFound().build();
            }
        }



}
