package pl.maja.service;

import org.springframework.stereotype.Service;
import pl.maja.model.Professor;
import pl.maja.repository.ProfessorRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProfessorService {

    private ProfessorRepository professorRepository;

    public ProfessorService(ProfessorRepository professorRepository) {
        this.professorRepository = professorRepository;
    }

    public List<Professor> getAllProf() {
        return professorRepository.findAll();
    }

    public Optional<Professor> getProfById(int id) {
        return professorRepository.findById(id);
    }

    public Professor createProf(Professor professor) {
        return  professorRepository.save(professor);
    }

    public void deleteProfessor(int id) {
        professorRepository.deleteById(id);
    }

}
