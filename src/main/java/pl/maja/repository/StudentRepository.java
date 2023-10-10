package pl.maja.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.maja.model.Student;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {


}
