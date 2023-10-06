package pl.maja.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.maja.model.Professor;
import pl.maja.model.Subject;
import pl.maja.service.SubjectService;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/subjects")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @PostMapping
    public Subject createSub(@RequestBody Subject subject) {
        return subjectService.createSubject(subject);
    }

    @GetMapping("/{id}")
    public Optional<Subject> getById(@PathVariable int id) {
        return subjectService.getSubjectById(id);
    }

    @GetMapping
    public List<Subject> listSubjects() {
        return subjectService.getAllSubjects();
    }


    @PutMapping("/{subjectId}/add_professor/{professorId}")
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

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateOnlyPart(@PathVariable int id, @RequestBody Subject subject) {
        try {
            subjectService.updateSubjectByID(id, subject);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}/list")
        public ResponseEntity<Set<Professor>> showAllProfOfParticularSubject(@PathVariable int id) {
        Set<Professor> profList = subjectService.showAllProfOfParticularSub(id);

        if (profList != null) {
            return ResponseEntity.ok(profList);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public void deleteSubject(@PathVariable int id) {
        subjectService.deleteSubject(id);
    }
    }