package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("student")
public class StudentController {

    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    @PostMapping
    public Student createStudent(@RequestBody Student student){
        return service.createStudent(student);
    }

    @GetMapping("{id}")
    public Optional<Student> findStudent(@PathVariable Long id){
        return service.findStudent(id);
    }

    @PutMapping
    public Student editStudent(@RequestBody Student student){
        return service.editStudent(student);
    }

    @DeleteMapping("{id}")
    public ResponseEntity removeStudent(@PathVariable Long id){
        service.deleteStudent(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public List<Student> getStudentByAge(@RequestParam int age){
        return service.filterStudentByAge(age);
    }

    @GetMapping("/find")
    public List<Student> findByAgeBetween(@RequestParam int min,@RequestParam int max){
        return service.findByAgeBetween(min, max);
    }

    @GetMapping("{id}/faculty")
    public Faculty getFaculty(@PathVariable Long id){
        return service.getFaculty(id);
    }

}
