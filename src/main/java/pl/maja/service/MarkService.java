package pl.maja.service;

import org.springframework.stereotype.Service;
import pl.maja.model.Mark;
import pl.maja.repository.MarkRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MarkService {

    private MarkRepository markRepository;

    public MarkService(MarkRepository markRepository) {
        this.markRepository = markRepository;
    }

    public List<Mark> getAllMarks() {
        return markRepository.findAll();
    }

    public Optional<Mark> getMarkById(int id) {
        return markRepository.findById(id);
    }

    public Mark createMark(Mark mark) {
        return  markRepository.save(mark);
    }

    public void deleteMark(int id) {
        markRepository.deleteById(id);
    }
}
