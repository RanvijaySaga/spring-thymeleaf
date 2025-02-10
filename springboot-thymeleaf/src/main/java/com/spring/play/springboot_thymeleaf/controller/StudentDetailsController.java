package com.spring.play.springboot_thymeleaf.controller;

import com.spring.play.springboot_thymeleaf.constants.ModelAtrributeName;
import com.spring.play.springboot_thymeleaf.dto.Student;
import com.spring.play.springboot_thymeleaf.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class StudentDetailsController {
    private final StudentService studentService;

    /**
     * When user type the URL localhost:8080/ on browser than request goes to the viewHomePage() method and in this method
     * we are fetching the list of student and added it into the modal with key, value pair and return the index.html page.
     * In index.html page the key {@link ModelAtrributeName.ALL_STUDENT_LIST} is identified as a java object and Thymeleaf iterate over the list and generate
     * dynamic content as per the user provided template.
     */
    @GetMapping("/")
    public String viewHomePage(Model model) {
        model.addAttribute(ModelAtrributeName.ALL_STUDENT_LIST, studentService.getAllStudents());
        return "index";
    }

    /**
     * when user clicks on Add student button than request goes to addNewstudent() method. And in this method we simply
     * create the empty object of the student and send it back to newstudent.html so that user can fill the data in this
     * empty object and when user hits on save button than /save mapping runs and get the object of the student and save
     * that object into database.
     *
     * @param model
     * @return
     */
    @GetMapping("/addnew")
    public String addNewStudent(Model model) {
        Student student = new Student();
        model.addAttribute(ModelAtrributeName.STUDENT, student);
        return "newStudent";
    }

    /**
     * When user hits on save button than request goes to saveStudent() method and in this method we are getting the object
     * of the student and save that object into the database.
     *
     * @param student
     * @return
     */

    @PostMapping("/save")
    public String saveStudent(@RequestBody Student student) {
        studentService.saveStudent(student);
        return "redirect:/";
    }

    // Get all students
    @GetMapping("/students")
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    // Get student by id
    @GetMapping("/students/{id}")
    public Student getStudent(@PathVariable Long id) {
        return studentService.getStudent(id);
    }

    // Update student
    /*
     * When user clicks on update button than request goes to updateStudent() method. And in this method we are getting the
     * student object by id and send it back to update.html so that user can update the data in this object and when user
     */
    @GetMapping("/updateForm/{id}")
    public String updateForm(@PathVariable Long id, Model model) {
        Student student = studentService.getStudent(id);
        model.addAttribute(ModelAtrributeName.STUDENT, student);
        return "update";

    }

    // Delete student
    @DeleteMapping("/students/{id}")
    public String deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return "redirect:/";
    }

}
