package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.FacultyService;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("faculty")
public class FacultyController {

    private final FacultyService service;

    public FacultyController (FacultyService service) {
        this.service = service;
    }

    @PostMapping
    public Faculty createFaculty(@RequestBody Faculty faculty){
        return service.createFaculty(faculty);
    }

    @GetMapping("{id}")
    public Optional<Faculty> findFaculty(@PathVariable Long id){
        return service.findFaculty(id);
    }

    @PutMapping
    public Faculty editFaculty(@RequestBody Faculty faculty){
        return service.editFaculty(faculty);
    }

    @DeleteMapping("{id}")
    public ResponseEntity removeFaculty(@PathVariable Long id){
        service.deleteFaculty(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public List<Faculty> getFacultyByColor(String color){
        return service.filterFacultyByColor(color);
    }
}
