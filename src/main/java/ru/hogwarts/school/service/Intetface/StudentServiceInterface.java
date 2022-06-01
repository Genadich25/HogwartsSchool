package ru.hogwarts.school.service.Intetface;

import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.List;
import java.util.Optional;

public interface StudentServiceInterface {

    public Student createStudent(Student student);

    public Optional<Student> findStudent(Long id);

    public Student editStudent(Student student);

    public void deleteStudent(Long id);

    public List<Student> filterStudentByAge(int age);

    public List<Student> findByAgeBetween(int min, int max);

    public Faculty getFaculty(Long id);

    public Integer getStudentCount();

    public Double getAgeAvg();

    public List<Student> getFiveLastStudents();

    List<Student> findByStudentsByChar(String symbol);

    Double getAvgAgeWithStream();
}
