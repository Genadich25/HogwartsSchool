package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.FacultyRepository;
import ru.hogwarts.school.service.Intetface.FacultyServiceInterface;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class FacultyService implements FacultyServiceInterface {
    private final FacultyRepository repository;

    private final Logger logger = LoggerFactory.getLogger(FacultyService.class);

    public FacultyService(FacultyRepository repository) {
        this.repository = repository;
    }

    public Faculty createFaculty(Faculty faculty) {
        logger.info("{} created and saved repository", faculty);
        return repository.save(faculty);
    }

    public Optional<Faculty> findFaculty(Long id) {
        logger.info("Find Faculty with {}", id);
        return repository.findById(id);
    }

    public Faculty editFaculty(Faculty faculty) {
        logger.info("{} updated repository", faculty);
        return repository.save(faculty);
    }

    public void deleteFaculty(Long id) {
        logger.info("Faculty with {} delete repository", id);
        repository.deleteById(id);
    }

    public List<Faculty> filterFacultyByColor(String color) {
        logger.info("Filtered Faculties by {}", color);
        return repository.getFacultyByColor(color);
    }

    public List<Faculty> findFacultyByColor(String color) {
        logger.info("Find Faculties by {}", color);
        return repository.findAllByColorIgnoreCase(color);
    }

    public List<Faculty> findFacultyByName(String name) {
        logger.info("Find Faculties by {}", name);
        return repository.findAllByNameIgnoreCase(name);
    }

    public List<Student> getStudents(Long id) {
        logger.info("Find student with {} in Faculty", id);
        Faculty faculty = repository.getById(id);
        return faculty.getStudents();
    }

    public Optional<String> getLongNameFaculty() {
        List<Faculty> faculties = repository.findAll();
        return faculties.stream()
                .map(Faculty::getName)
                .max(Comparator.comparingInt(String::length));
    }

    public Integer getIterate(){
        return Stream.iterate(1, a -> a + 1)
                .parallel()
                .limit(1_000_000)
                .reduce(0, Integer::sum);
    }
}
