package com.academy.marker.Services;

import com.academy.marker.DTO.StudentRequestDTO;
import com.academy.marker.Repos.ClassRepository;
import com.academy.marker.Repos.StudentRepository;
import com.academy.marker.entity.ClassEntity;
import com.academy.marker.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ClassRepository classRepository;

    public Student saveStudent(StudentRequestDTO studentDTO) {
        Optional<ClassEntity> classEntity = classRepository.findById(studentDTO.getClassId());
        if (classEntity.isEmpty()) {
            throw new RuntimeException("Class not found");
        }

        Student student = new Student();
        student.setFirstName(studentDTO.getFirstName());
        student.setLastName(studentDTO.getLastName());
        student.setDateOfBirth(LocalDate.parse(studentDTO.getDateOfBirth()));
        student.setStudentClass(classEntity.get());

        return studentRepository.save(student);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public void deleteStudent(Long id) {
        if (!studentRepository.existsById(id)) {
            throw new RuntimeException("Student not found");
        }
        studentRepository.deleteById(id);
    }

    private StudentRequestDTO convertToDTO(Student student) {
        StudentRequestDTO dto = new StudentRequestDTO();
        dto.setFirstName(student.getFirstName());
        dto.setLastName(student.getLastName());
        dto.setDateOfBirth(student.getDateOfBirth().toString());
        dto.setClassId(student.getStudentClass().getId());
        return dto;
    }
}
