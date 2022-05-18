package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.FacultyRepository;
import ru.hogwarts.school.service.Intetface.FacultyServiceInterface;

import java.util.List;
import java.util.Optional;

@Service
public class FacultyService implements FacultyServiceInterface {
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

    public List<Faculty> findFacultyByColor(String color){
        return repository.findAllByColorIgnoreCase(color);
    }

    public List<Faculty> findFacultyByName(String name){
        return repository.findAllByNameIgnoreCase(name);
    }

    public List<Student> getStudents(Long id){
        Faculty faculty = repository.getById(id);
        return faculty.getStudents();
    }
}
