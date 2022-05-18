package ru.hogwarts.school.controller;

import org.apache.catalina.connector.Request;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cassandra.CassandraProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.ArrayList;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StudentControllerTest {
    private final Student student = new Student();
    private final Faculty faculty = new Faculty();

    private final static String NAME = "Name";
    private final static int AGE = 20;
    private final static Long ID = 1L;
    private final static String NAME_FACULTY = "Name Faculty";
    private final static String COLOR = "Color";
    private final static Long ID_FACULTY = 10L;

    @LocalServerPort
    private int port;

    @InjectMocks
    private StudentController studentController;

    @Autowired
    private TestRestTemplate restTemplate;

    @BeforeEach
    public void initOut(){
        faculty.setColor(COLOR);
        faculty.setId(ID_FACULTY);
        faculty.setName(NAME_FACULTY);
        faculty.setStudents(new ArrayList<>());
        student.setName(NAME);
        student.setAge(AGE);
        student.setId(ID);
        student.setFaculty(faculty);
    }

    @Test
    public void contextLoads() throws Exception {
        Assertions.assertThat(studentController).isNotNull();
    }

    @Test
    public void testCreateStudent() {
        Assertions.assertThat(this.restTemplate.postForObject("http://localhost:" + port + "/student", student, String.class))
                .isNotNull();
    }

    @Test
    public void testFindStudent() {
        Assertions.assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/student" + ID, String.class))
                .isNotNull();
    }

    @Test
    public void testEditStudent() {
        HttpEntity<Student> entity = new HttpEntity<Student>(student);
        ResponseEntity<Student> response = this.restTemplate.exchange("http://localhost:" + port + "/student", HttpMethod.PUT, entity, Student.class);
        org.junit.jupiter.api.Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void testDeleteStudent() {
        HttpEntity<Student> entity = new HttpEntity<Student>(student);
        ResponseEntity<Student> response = restTemplate.exchange("http://localhost:" + port + "/student/" + ID, HttpMethod.DELETE, entity, Student.class);
        org.junit.jupiter.api.Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void getStudentByAge(){
        Assertions.assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/student", String.class))
                .isNotNull();
    }

    @Test
    public void findByAgeBetween(){
        Assertions.assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/student/find", String.class))
                .isNotNull();
    }

    @Test
    public void getFaculty(){
        Assertions.assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/" + 5 + "/faculty", String.class))
                .isNotNull();
    }

    @Test
    public void uploadAvatar(){
        Assertions.assertThat(this.restTemplate.postForObject("http://localhost:" + port + "/student/" + ID + "/avatar", student, String.class))
                .isNotNull();
    }

    @Test
    public void downloadAvatarPreview(){
        Assertions.assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/" + 5 + "/avatar/preview", String.class))
                .isNotNull();
    }

    @Test
    public void downloadAvatar(){
        Assertions.assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/" + 5 + "/avatar", String.class))
                .isNotNull();
    }
}
