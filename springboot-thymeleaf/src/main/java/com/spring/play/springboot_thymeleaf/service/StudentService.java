package com.spring.play.springboot_thymeleaf.service;

import com.spring.play.springboot_thymeleaf.dto.Student;
import com.spring.play.springboot_thymeleaf.exception.StudentManagementException;
import com.spring.play.springboot_thymeleaf.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    /**
     * Add student
     *
     * @param student the student
     */
    public void addStudent(Student student) {
        studentRepository.save(student);
    }

    /**
     * Get all students
     *
     * @return List<Student>
     */
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    /**
     * Get student by id
     * If student exists, return the student
     * If student does not exist, throw StudentManagementException
     *
     * @param id
     * @return Student
     */
    public Student getStudent(Long id) {
        return studentRepository.findById(id).orElseThrow(() -> new StudentManagementException("Student not found with Id : " + id));
    }

    /**
     * Get student by id
     * If student exists, update the student
     * If student does not exist, throw StudentManagementException
     *
     * @param id
     * @return void
     */
    public void updateStudent(Long id, Student student) {
        // Check if student exists
        // If student exists, update the student
        // If student does not exist, throw StudentManagementException

        studentRepository.findById(id).ifPresentOrElse(
                student1 -> {
                    student1.setName(student.getName());
                    student1.setClassNumber(student.getClassNumber());
                    student1.setSection(student.getSection());
                    studentRepository.save(student1);
                },
                () -> {
                    throw new StudentManagementException("Student not found with Id : " + id);
                }
        );

    }

    /**
     * Delete student by id
     * If student exists, delete the student
     * If student does not exist, throw StudentManagementException
     *
     * @param id
     */
    public void deleteStudent(Long id) {
        studentRepository.findById(id).ifPresentOrElse(
                studentRepository::delete,
                () -> {
                    throw new StudentManagementException("Student not found with Id : " + id);
                }
        );
        // same can be written as this :
        // studentRepository.delete(studentRepository.findById(id).orElseThrow(() -> new StudentManagementException("Student not found with Id : " + id)));
    }

}
