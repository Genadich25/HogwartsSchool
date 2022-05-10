package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository repository;

    public StudentService(StudentRepository studentRepository) {
        this.repository = studentRepository;
    }

    public Student createStudent(Student student){
        return repository.save(student);
    }

    public Optional<Student> findStudent(Long id){
        return repository.findById(id);
    }

    public Student editStudent(Student student){
        return repository.save(student);
    }

    public void deleteStudent(Long id){
        repository.deleteById(id);
    }

    public List<Student> filterStudentByAge(int age){
        return repository.getStudentByAge(age);
    }

    public List<Student> findByAgeBetween(int min, int max){
        return repository.findByAgeBetween(min, max);
    }

    public Faculty getFaculty(Long id){
        Optional<Student> student = findStudent(id);
        return student.get().getFaculty();
    }
}
