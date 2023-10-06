package pl.maja.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import pl.maja.model.Professor;
import pl.maja.model.Subject;
import pl.maja.repository.ProfessorRepository;
import pl.maja.repository.SubjectRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class SubjectService {

    private SubjectRepository subjectRepository;
    private ProfessorRepository professorRepository;


    public SubjectService(SubjectRepository subjectRepository, ProfessorRepository professorRepository) {
        this.subjectRepository = subjectRepository;
        this.professorRepository = professorRepository;
    }

    public List<Subject> getAllSubjects() {
        return subjectRepository.findAll();
    }

    public Optional<Subject> getSubjectById(int id) {
        return subjectRepository.findById(id);
    }

    public Subject createSubject(Subject subject) {
        return  subjectRepository.save(subject);
    }

    public void deleteSubject(int id) {
        subjectRepository.deleteById(id);
    }


    public void addProfToSubject(int subjectId, int professorId) {
        Subject subject = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new EntityNotFoundException("Nie znaleziono przedmiotu o podanym ID"));

        Professor professor = professorRepository.findById(professorId)
                .orElseThrow(() -> new EntityNotFoundException("Nie znaleziono profesora o podanym ID"));

        subject.addProfessor(professor);
        subjectRepository.save(subject);

    }

    public void updateSubjectByID(int id, Subject updatedSubject) {
        Optional<Subject> existingSubject = subjectRepository.findById(id);

        if(existingSubject.isPresent()) {
            Subject subject = existingSubject.get();

            if (updatedSubject.getName() != null) {
                subject.setName(updatedSubject.getName());
            }
            subjectRepository.save(subject);
        } else {
            System.out.println("There is no subject with this name.");
        }
    }

    public Set<Professor> showAllProfOfParticularSub(int subjectId) {
        Optional<Subject> subject = subjectRepository.findById(subjectId);
        Set<Professor> profList = null;

        if (subject != null) {
            Subject sub = subject.get();
            profList = sub.getProfessors();

        }
        return profList;
    }
}
