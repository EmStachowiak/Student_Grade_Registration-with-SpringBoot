package pl.maja.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Subject {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    private String name;

    @ManyToMany
    @JoinTable(name="professors_subjects", joinColumns = @JoinColumn(name="id_subject", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name="id_professore", referencedColumnName = "id"))
    private List<Professor> professors;

    @ManyToOne
    @JoinColumn(name="student_id")
    private Student student;

    @OneToMany(mappedBy = "subject")
    private List<Mark> marks;


    public Subject() {
        professors = new ArrayList<>();
        marks = new ArrayList<>();
    }

    public Subject(String name) {
        this.name = name;
        professors = new ArrayList<>();
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

    public List<Professor> getProfessors() {
        return professors;
    }

    public void setProfessors(List<Professor> professors) {
        this.professors = professors;
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
