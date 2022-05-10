package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repository.FacultyRepository;

import java.util.List;
import java.util.Optional;

@Service
public class FacultyService {
    private final FacultyRepository repository;

    public FacultyService(FacultyRepository repository) {
        this.repository = repository;
    }

    public Faculty createFaculty(Faculty faculty){
        return repository.save(faculty);
    }

    public Optional<Faculty> findFaculty(Long id){
        return repository.findById(id);
    }

    public Faculty editFaculty(Faculty faculty){
        return repository.save(faculty);
    }

    public void deleteFaculty(Long id){
        repository.deleteById(id);
    }

    public List<Faculty> filterFacultyByColor(String color){
        return repository.getFacultyByColor(color);
    }
}
