package pl.maja.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.maja.model.Subject;

import java.util.Set;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Integer> {

    //public void saveAll(Set<Subject> subjects);
}
