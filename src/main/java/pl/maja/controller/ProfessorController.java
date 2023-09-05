package pl.maja.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.maja.model.Professor;
import pl.maja.service.ProfessorService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/professors")
public class ProfessorController {

    @Autowired
    private ProfessorService professorService;

    @PostMapping
    public Professor createProf(@RequestBody  Professor prof) {
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


    

}
