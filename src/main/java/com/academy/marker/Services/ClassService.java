package com.academy.marker.Services;

import com.academy.marker.DTO.ClassRequestDTO;
import com.academy.marker.Repos.ClassRepository;
import com.academy.marker.entity.ClassEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassService {

    @Autowired
    private ClassRepository classRepository;

    public List<ClassEntity> getAllClasses() {
        return classRepository.findAll();
    }

    public ClassEntity saveClass(ClassRequestDTO classDTO) {
        ClassEntity classEntity = new ClassEntity();
        classEntity.setName(classDTO.getName());
        classEntity.setSection(classDTO.getSection());

        return classRepository.save(classEntity);
    }
}
