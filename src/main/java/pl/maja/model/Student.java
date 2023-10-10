package pl.maja.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Student {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    private String name;
    private String surname;
    private String schoolClass;

    @Transient
    private  static Set<Student> sameClassSet = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JsonIgnore
    @JoinTable(name="students_subjects", joinColumns = @JoinColumn(name="id_student", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name="id_subject", referencedColumnName = "id"))
    private Set<Subject> subjects;

    public Student(String name, String surname, String schoolClass) {
        this.name = name;
        this.surname = surname;
        this.schoolClass = schoolClass;
        subjects = new HashSet<>();
    }

    public Student() {
        subjects = new HashSet<>();
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getSchoolClass() {
        return schoolClass;
    }

    public void setSchoolClass(String schoolClass) {
        this.schoolClass = schoolClass;
    }

    public Set<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(Set<Subject> subjects) {
        this.subjects = subjects;
    }


    public void addSubject(Subject subject) {
        this.subjects.add(subject);
        subject.getStudents().add(this);
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", schoolClass='" + schoolClass + '\'' +
                ", subjects=" + subjects +
                '}';
    }
}
