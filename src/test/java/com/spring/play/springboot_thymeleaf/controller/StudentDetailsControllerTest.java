package com.spring.play.springboot_thymeleaf.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.play.springboot_thymeleaf.constants.ModelAtrributeName;
import com.spring.play.springboot_thymeleaf.dto.Student;
import com.spring.play.springboot_thymeleaf.service.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.Collections;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class StudentDetailsControllerTest {

    private MockMvc mockMvc;

    @Mock
    private StudentService studentService;

    @InjectMocks
    private StudentDetailsController studentDetailsController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(studentDetailsController).build();
    }

    @Test
    void testViewHomePage() throws Exception {
        when(studentService.getAllStudents()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attribute(ModelAtrributeName.ALL_STUDENT_LIST, Collections.emptyList()));

        verify(studentService, times(1)).getAllStudents();
    }

    @Test
    void testAddNewStudent() throws Exception {
        mockMvc.perform(get("/addNewStudent"))
                .andExpect(status().isOk())
                .andExpect(view().name("newStudent"))
                .andExpect(model().attributeExists(ModelAtrributeName.STUDENT));
    }

    @Test
    void testSaveStudent() throws Exception {
        Student student = new Student();

        mockMvc.perform(post("/save")
                        .flashAttr("student", student))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));

        verify(studentService, times(1)).saveStudent(student);
    }

    @Test
    void testGetAllStudents() throws Exception {
        when(studentService.getAllStudents()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/getAllStudents"))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));

        verify(studentService, times(1)).getAllStudents();
    }

    @Test
    void testGetStudent() throws Exception {
        Student student = new Student();
        /*student.setId(1L);
        student.setName("John");
        student.setClassNumber(10);
        student.setSection("A");
        ObjectMapper objectMapper = new ObjectMapper();
        String studentJson = objectMapper.writeValueAsString(student);
         */
        when(studentService.getStudent(1L)).thenReturn(student);

        mockMvc.perform(get("/students/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().json("{}"));
                //.andExpect(content().json(studentJson));

        verify(studentService, times(1)).getStudent(1L);
    }

    @Test
    void testUpdateForm() throws Exception {
        Student student = new Student();
        when(studentService.getStudent(1L)).thenReturn(student);

        mockMvc.perform(get("/updateForm/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("update"))
                .andExpect(model().attribute(ModelAtrributeName.STUDENT, student));

        verify(studentService, times(1)).getStudent(1L);
    }

    @Test
    void testDeleteStudent() throws Exception {
        mockMvc.perform(get("/deleteStudent/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));

        verify(studentService, times(1)).deleteStudent(1L);
    }
}