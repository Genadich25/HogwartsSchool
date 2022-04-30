package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private Map<Long, Student> students = new HashMap<>();
    private Long lastId = 0L;

    public Student createStudent(Student student){
        student.setId(++lastId);
        students.put(lastId, student);
        return student;
    }

    public Student findStudent(Long id){
        return students.get(id);
    }

    public Student editStudent(Student student){
        students.put(student.getId(), student);
        return student;
    }

    public Student deleteStudent(Long id){
        return students.remove(id);
    }

    public Map<Long, Student> filterStudentByAge(int age){
        return students.entrySet().stream()
                .filter(students -> students.getValue().getAge() == age)
                .collect(Collectors.toMap(k -> k.getValue().getId() , v -> v.getValue()));

    }
}
