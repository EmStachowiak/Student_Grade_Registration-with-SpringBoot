package pl.maja.service;

import org.springframework.stereotype.Service;
import pl.maja.model.Professor;
import pl.maja.model.Subject;
import pl.maja.repository.SubjectRepository;

import java.util.List;
import java.util.Optional;

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
}
