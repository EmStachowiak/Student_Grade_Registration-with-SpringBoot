package pl.maja.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.maja.model.Mark;


@Repository
public interface MarkRepository extends JpaRepository<Mark, Integer> {
}
