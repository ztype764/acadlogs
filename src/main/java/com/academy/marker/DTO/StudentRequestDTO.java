package com.academy.marker.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentRequestDTO {
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private Long classId; // ID of the class the student belongs to
}
