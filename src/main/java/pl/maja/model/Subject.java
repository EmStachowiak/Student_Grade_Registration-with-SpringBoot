package pl.maja.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Subject {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    private String name;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE,
                    CascadeType.DETACH

            })
    @JoinTable(name="professors_subjects", joinColumns = @JoinColumn(name="id_subject", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name="id_professore", referencedColumnName = "id"))
    private Set<Professor> professors;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name="student_id")
    private Student student;

    @OneToMany(mappedBy = "subject")
    private List<Mark> marks;


    public Subject() {
        professors = new HashSet<>();
        marks = new ArrayList<>();
    }

    public Subject(String name) {
        this.name = name;
        professors = new HashSet<>();
        marks = new ArrayList<>();
    }

    public int getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Set<Professor> getProfessors() {
        return professors;
    }

    public void setProfessors(Set<Professor> professors) {
        this.professors = professors;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public List<Mark> getMarks() {
        return marks;
    }

    public void setMarks(List<Mark> marks) {
        this.marks = marks;
    }

    public void addProfessor(Professor professor) {
        this.professors.add(professor);
        professor.addSubject(this);
        professor.getSubjects().add(this);
    }

    public void removeProfessor(int professorId) {
        Professor professor = this.professors.stream().filter( p -> p.getId() == professorId).findFirst().orElse(null);
        if (professor != null) {
            this.professors.remove(professor);
            professor.getSubjects().remove(this);
        }
    }


    @Override
    public String toString() {
        return "Subject{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", professors=" + professors +
                '}';
    }
}
