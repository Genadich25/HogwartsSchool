package ru.hogwarts.school.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;


@Entity
public class Faculty {
    private String name;
    private String color;

    @OneToMany(mappedBy = "faculty")
    private List<Student> students;

    @Id
    @GeneratedValue
    private Long id;

    public Faculty() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @JsonManagedReference
    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Faculty faculty = (Faculty) o;
        return Objects.equals(name, faculty.name) && Objects.equals(color, faculty.color) && id.equals(faculty.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, color, id);
    }

    @Override
    public String toString() {
        return "Faculty{" +
                "name='" + name + '\'' +
                ", color='" + color + '\'' +
                ", id=" + id +
                '}';
    }
}
