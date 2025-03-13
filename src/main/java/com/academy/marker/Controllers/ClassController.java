package com.academy.marker.Controllers;

import com.academy.marker.DTO.ClassRequestDTO;
import com.academy.marker.Services.ClassService;
import com.academy.marker.entity.ClassEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/classes")
public class ClassController {

    @Autowired
    private ClassService classService;

    @PostMapping
    public ClassEntity saveClass(@ModelAttribute ClassRequestDTO classDTO) {
        return classService.saveClass(classDTO);
    }

    @GetMapping
    public List<ClassEntity> getAllClasses() {
        return classService.getAllClasses();
    }

    @DeleteMapping("/{id}")
    public void deleteClass(@PathVariable Long id) {
        classService.deleteClass(id);
    }
}
