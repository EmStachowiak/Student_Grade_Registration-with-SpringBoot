package pl.maja.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.maja.model.Professor;
import pl.maja.model.Subject;
import pl.maja.service.ProfessorService;
import pl.maja.service.SubjectService;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/subjects")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;
    @Autowired
    private ProfessorService professorService;

    @PostMapping
    public Subject createSub(@RequestBody Subject subject) {
        return subjectService.createSubject(subject);
    }

    @GetMapping
    public List<Subject> listSubjects() {
        return subjectService.getAllSubjects();
    }


//    @PatchMapping("/{id}/add_professors")
//    public ResponseEntity<Void> addProfToSubject(@PathVariable int id, @RequestBody List<Professor> professors) {
//        try {
//            subjectService.addProfToSubject(id, professors);
//            return ResponseEntity.noContent().build();
//        } catch (Exception e) {
//            return ResponseEntity.notFound().build();
//        }
//
//    }




}
