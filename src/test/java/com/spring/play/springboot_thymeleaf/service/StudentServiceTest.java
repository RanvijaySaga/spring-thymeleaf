package com.spring.play.springboot_thymeleaf.service;

import com.spring.play.springboot_thymeleaf.dto.Student;
import com.spring.play.springboot_thymeleaf.exception.StudentManagementException;
import com.spring.play.springboot_thymeleaf.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentService studentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveStudent() {
        Student student = new Student();
        studentService.saveStudent(student);
        verify(studentRepository, times(1)).save(student);
    }

    @Test
    void testGetAllStudents() {
        List<Student> students = Arrays.asList(new Student(), new Student());
        when(studentRepository.findAll()).thenReturn(students);
        List<Student> result = studentService.getAllStudents();
        assertEquals(2, result.size());
    }

    @Test
    void testGetStudent() {
        Student student = new Student();
        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));
        Student result = studentService.getStudent(1L);
        assertEquals(student, result);
    }

    @Test
    void testGetStudentNotFound() {
        when(studentRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(StudentManagementException.class, () -> studentService.getStudent(1L));
    }

    @Test
    void testUpdateStudent() {
        Student existingStudent = new Student();
        Student updatedStudent = new Student();
        updatedStudent.setName("Updated Name");
        when(studentRepository.findById(1L)).thenReturn(Optional.of(existingStudent));
        studentService.updateStudent(1L, updatedStudent);
        verify(studentRepository, times(1)).save(existingStudent);
        assertEquals("Updated Name", existingStudent.getName());
    }

    @Test
    void testUpdateStudentNotFound() {
        Student updatedStudent = new Student();
        when(studentRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(StudentManagementException.class, () -> studentService.updateStudent(1L, updatedStudent));
    }

    @Test
    void testDeleteStudent() {
        Student student = new Student();
        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));
        studentService.deleteStudent(1L);
        verify(studentRepository, times(1)).delete(student);
    }

    @Test
    void testDeleteStudentNotFound() {
        when(studentRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(StudentManagementException.class, () -> studentService.deleteStudent(1L));
    }
}