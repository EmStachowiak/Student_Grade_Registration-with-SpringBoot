package pl.maja.service;

import org.springframework.stereotype.Service;
import pl.maja.model.Professor;
import pl.maja.model.Subject;
import pl.maja.repository.SubjectRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class SubjectService {

    private SubjectRepository subjectRepository;

    public SubjectService(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
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

    public void addProfToSubject(int id, Set<Professor> professors) {
        Subject subject = subjectRepository.findById(id).get();
        subject.setProfessors(professors);
        subjectRepository.save(subject);

    }

    public List<Subject> saveSubjects(List<Subject> subjects) {
        return subjectRepository.saveAll(subjects);
    }

//    public void addProfessorsToSubject(int subjectId, List<Professor> professors) {
//        Subject subject = subjectRepository.findById(subjectId).orElseThrow();
//        subject.getProfessors().addAll(professors);
//        subjectRepository.save(subject);
//    }

}
