package pl.maja.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import pl.maja.model.Professor;
import pl.maja.model.Subject;
import pl.maja.repository.ProfessorRepository;
import pl.maja.repository.SubjectRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

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



    public void updateProfByID(int id, Professor updatedProf) {
        Optional<Professor> existingProf = professorRepository.findById(id);

        if(existingProf.isPresent()) {
            Professor professor= existingProf.get();

            if (updatedProf.getName() != null) {
                professor.setName(updatedProf.getName());
            }
            if (updatedProf.getSurname() != null) {
                professor.setSurname(updatedProf.getSurname());
            }
            professorRepository.save(professor);
        } else {
            System.out.println("There is no proff with this name.");
        }
    }

    @Transactional  // jeśli jedna rzecz się nie powiedzie to wszystko z tej metody zostanie cofniete
    public void deleteProfessor(int professorId) {
        Professor professor = professorRepository.findById(professorId).orElse(null);
        if (professor != null) {
            Set<Subject> subjects = professor.getSubjects();
            for (Subject subject : subjects) {
                subject.getProfessors().remove(professor);
            }
            professorRepository.delete(professor);
        }
    }

}
