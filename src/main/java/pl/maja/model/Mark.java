package pl.maja.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class Mark {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    private int value;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name="subject_id")
    private Subject subject;

    public Mark(int value) {
        this.value = value;
    }

    public Mark() {
    }

    public Mark(int value, Subject subject) {
        this.value = value;
        this.subject = subject;
    }

    public int getId() {
        return id;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    @Override
    public String toString() {
        return "Mark{" +
                "id=" + id +
                ", value=" + value +
                ", subject=" + subject +
                '}';
    }


}
