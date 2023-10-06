package pl.maja.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.maja.model.Professor;
import pl.maja.model.Subject;
import pl.maja.repository.ProfessorRepository;
import pl.maja.repository.SubjectRepository;
import pl.maja.service.ProfessorService;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/professors")
public class ProfessorController {

    @Autowired
    private ProfessorService professorService;


    @PostMapping
    public Professor createProf(@RequestBody Professor prof) {
        return professorService.createProf(prof);
    }

    @GetMapping("/{id}")
    public Optional<Professor> getById(@PathVariable int id) {
        return professorService.getProfById(id);
    }

    @GetMapping
    public List<Professor> showAll() {
        return professorService.getAllProf();
    }

    @DeleteMapping("/{id}")
    public void deleteProf(@PathVariable int id) {
        professorService.deleteProfessor(id);
    }


//    @PatchMapping("/{id}")
//    public void updateProffesor(@PathVariable int id, @RequestBody Professor professor) {
//        professorService.updateProfByID(id, professor);
//    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateOnlyPart(@PathVariable int id, @RequestBody Professor professor) {
        try {
            professorService.updateProfByID(id, professor);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }


}