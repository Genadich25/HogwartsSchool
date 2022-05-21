package ru.hogwarts.school.controller;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.AvatarRepository;
import ru.hogwarts.school.repository.FacultyRepository;
import ru.hogwarts.school.repository.StudentRepository;
import ru.hogwarts.school.service.AvatarService;
import ru.hogwarts.school.service.FacultyService;
import ru.hogwarts.school.service.StudentService;

import java.util.ArrayList;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class FacultyControllerTest {
    private final Student student = new Student();
    private final Faculty faculty = new Faculty();
    private final JSONObject jsonFaculty = new JSONObject();

    private final static String NAME = "Name";
    private final static int AGE = 20;
    private final static Long ID = 1L;
    private final static String NAME_FACULTY = "Name Faculty";
    private final static String COLOR = "Color";
    private final static Long ID_FACULTY = 10L;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FacultyRepository facultyRepository;

    @MockBean
    private StudentRepository studentRepository;

    @MockBean
    private AvatarRepository avatarRepository;

    @SpyBean
    private FacultyService facultyService;

    @SpyBean
    private StudentService studentService;

    @SpyBean
    private AvatarService avatarService;

    @InjectMocks
    private FacultyController facultyController;
    private StudentController studentController;

    @BeforeEach
    public void initOut() throws JSONException {
        faculty.setColor(COLOR);
        faculty.setId(ID_FACULTY);
        faculty.setName(NAME_FACULTY);
        faculty.setStudents(new ArrayList<>());
        student.setName(NAME);
        student.setAge(AGE);
        student.setId(ID);
        student.setFaculty(faculty);
        jsonFaculty.put("name", NAME_FACULTY);
        jsonFaculty.put("color", COLOR);

        Mockito.when(facultyRepository.save(any(Faculty.class))).thenReturn(faculty);
        Mockito.when(facultyRepository.findById(any(Long.class))).thenReturn(Optional.of(faculty));
        Mockito.when(facultyRepository.getById(any(Long.class))).thenReturn(faculty);
    }

    @Test
    public void createFaculty() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/faculty") //send
                        .content(jsonFaculty.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) //receive
                .andExpect(jsonPath("$.id").value(ID_FACULTY))
                .andExpect(jsonPath("$.name").value(NAME_FACULTY))
                .andExpect(jsonPath("$.color").value(COLOR));
    }

    @Test
    public void findFaculty() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty/" + ID_FACULTY) //send
                        .content(jsonFaculty.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) //receive
                .andExpect(jsonPath("$.id").value(ID_FACULTY))
                .andExpect(jsonPath("$.name").value(NAME_FACULTY))
                .andExpect(jsonPath("$.color").value(COLOR));
    }

    @Test
    public void editFaculty() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .put("/faculty") //send
                        .content(jsonFaculty.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) //receive
                .andExpect(jsonPath("$.id").value(ID_FACULTY))
                .andExpect(jsonPath("$.name").value(NAME_FACULTY))
                .andExpect(jsonPath("$.color").value(COLOR));
    }

    @Test
    public void deleteFaculty() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/faculty/" + ID_FACULTY) //send
                        .content(jsonFaculty.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void getFacultyByColor() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty/find?color=red") //send
                        .content(jsonFaculty.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty/find?name=Cool") //send
                        .content(jsonFaculty.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void getStudents() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty/" + ID_FACULTY + "/students") //send
                        .content(jsonFaculty.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
