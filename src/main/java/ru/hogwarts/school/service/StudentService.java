package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;
import ru.hogwarts.school.service.Intetface.StudentServiceInterface;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService implements StudentServiceInterface {

    private final StudentRepository repository;

    private final Logger logger = LoggerFactory.getLogger(StudentService.class);

    public StudentService(StudentRepository studentRepository) {
        this.repository = studentRepository;
    }

    public Student createStudent(Student student){
        logger.info("{} created and saved repository", student);
        return repository.save(student);
    }

    public Optional<Student> findStudent(Long id){
        logger.info("Find Student with {}", id);
        return repository.findById(id);
    }

    public Student editStudent(Student student){
        logger.info("{} updated repository", student);
        return repository.save(student);
    }

    public void deleteStudent(Long id){
        logger.info("Student with {} delete repository", id);
        repository.deleteById(id);
    }

    public List<Student> filterStudentByAge(int age){
        logger.info("Filtered Students by {}", age);
        return repository.getStudentByAge(age);
    }

    public List<Student> findByAgeBetween(int min, int max){
        logger.info("Find Students between {} and {}", min, max);
        return repository.findByAgeBetween(min, max);
    }

    public Faculty getFaculty(Long id){
        Optional<Student> student = findStudent(id);
        logger.info("Find Student's Faculty {}", id);
        return student.get().getFaculty();
    }

    public Integer getStudentCount() {
        logger.info("Get student count");
        return repository.getStudentCount();
    }

    public Double getAgeAvg() {
        logger.info("Get average age of students");
        return repository.getAgeAvg();
    }

    public List<Student> getFiveLastStudents() {
        logger.info("Get five last students");
        return repository.getFiveLastStudents();
    }
}
