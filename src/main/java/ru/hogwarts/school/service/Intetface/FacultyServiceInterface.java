package ru.hogwarts.school.service.Intetface;

import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.List;
import java.util.Optional;

public interface FacultyServiceInterface {

    public Faculty createFaculty(Faculty faculty);

    public Optional<Faculty> findFaculty(Long id);

    public Faculty editFaculty(Faculty faculty);

    public void deleteFaculty(Long id);

    public List<Faculty> filterFacultyByColor(String color);

    public List<Faculty> findFacultyByColor(String color);

    public List<Faculty> findFacultyByName(String name);

    public List<Student> getStudents(Long id);

    Optional<String> getLongNameFaculty();

    Integer getIterate();
}
