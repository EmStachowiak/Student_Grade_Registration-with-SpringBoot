package pl.maja.service;

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
    private SubjectRepository subjectRepository;

    public ProfessorService(ProfessorRepository professorRepository, SubjectRepository subjectRepository) {
        this.professorRepository = professorRepository;
        this.subjectRepository = subjectRepository;
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
//            if (updatedProf.getSubjects() != null || updatedProf.getSubjects() == null){
//                professor.setSubjects(updatedProf.getSubjects());
//            }
            professorRepository.save(professor);
        } else {
            System.out.println("There is no proff with this name.");
        }
    }







//    public void addSubjectsToProf(int id, Set<Subject> subjects) {
//        Professor professor = professorRepository.findById(id).get();
//        professor.setSubjects(subjects);
//
//        professorRepository.save(professor);
//      //  subjectRepository.saveAll(subjects);
//    }

    public Professor addSubject(int idProfessor, int idSubject){

        Professor updatedProf = professorRepository.findById(idProfessor).get();
        Subject subject =subjectRepository.findById(idSubject).get();

        updatedProf.addSubject(subject);
        professorRepository.save(updatedProf);
        subjectRepository.save(subject);

        return updatedProf;
    }


// NIE DZIAŁA
//    public Professor addSubjectToProfessor(int professorId, Subject subject) {
//        Optional<Professor> profToUpdate = professorRepository.findById(professorId);
//
//        if (profToUpdate.isPresent()) {
//            Professor professor = profToUpdate.get();
//            professor.addSubject(subject);
//            return professorRepository.save(professor);
//        } else {
//            throw new IllegalArgumentException("Professor with ID " + professorId + " not found.");
//        }
//    }
}
