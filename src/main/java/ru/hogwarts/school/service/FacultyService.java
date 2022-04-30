package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FacultyService {
    private Map<Long, Faculty> faculties = new HashMap<>();
    private Long lastId = 0L;

    public Faculty createFaculty(Faculty faculty){
        faculty.setId(++lastId);
        faculties.put(lastId, faculty);
        return faculty;
    }

    public Faculty findFaculty(Long id){
        return faculties.get(id);
    }

    public Faculty editFaculty(Faculty faculty){
        faculties.put(faculty.getId(), faculty);
        return faculty;
    }

    public Faculty deleteFaculty(Long id){
        return faculties.remove(id);
    }

    public Map<Long, Faculty> filterFacultyByColor(String color){
        return faculties.entrySet().stream()
                .filter(f -> f.getValue().getColor().equals(color))
                .collect(Collectors.toMap(k -> k.getValue().getId() , v -> v.getValue()));

    }
}
