package com.academy.marker.Controllers;

import com.academy.marker.DTO.StudentRequestDTO;
import com.academy.marker.Services.StudentService;
import com.academy.marker.entity.Student;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping
    public Student saveStudent(@ModelAttribute StudentRequestDTO studentDTO, HttpServletRequest request) {
        String role = (String) request.getAttribute("role");
        if (!"ADMIN".equals(role)) {
            throw new RuntimeException("Only admins can add students");
        }
        return studentService.saveStudent(studentDTO);
    }

    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable Long id, HttpServletRequest request) {
        String role = (String) request.getAttribute("role");
        if (!"ADMIN".equals(role)) {
            throw new RuntimeException("Only admins can delete students");
        }
        studentService.deleteStudent(id);
    }
}
