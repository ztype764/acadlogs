package com.academy.marker.Services;

import com.academy.marker.DTO.JuniorReportCardDTO;
import com.academy.marker.Repos.JuniorReportCardRepository;
import com.academy.marker.Repos.StudentRepository;
import com.academy.marker.entity.JuniorReportCard;
import com.academy.marker.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class JuniorReportCardService {

    @Autowired
    private JuniorReportCardRepository juniorReportCardRepository;

    @Autowired
    private StudentRepository studentRepository;

    public String createJuniorReportCard(JuniorReportCardDTO dto) {
        Optional<Student> studentOpt = studentRepository.findById(dto.getStudentId());
        if (studentOpt.isEmpty()) {
            return "Student not found";
        }

        JuniorReportCard reportCard = new JuniorReportCard();
        reportCard.setStudent(studentOpt.get());
        reportCard.setExamType(dto.getExamType());
        reportCard.setTotalObtainedMarks(dto.getTotalObtainedMarks());
        reportCard.setTotalMaxMarks(dto.getTotalMaxMarks());
        reportCard.setOverallGrade(dto.getOverallGrade());
        reportCard.setClassPosition(dto.getClassPosition());
        reportCard.setPercentage(dto.getPercentage());
        reportCard.setAttendance(dto.getAttendance());
        reportCard.setFinalVerdict(dto.getFinalVerdict());
        reportCard.setIsPassed(dto.getIsPassed());

        juniorReportCardRepository.save(reportCard);
        return "Junior report card created successfully";
    }

    public JuniorReportCardDTO getJuniorReportCard(Long id) {
        return juniorReportCardRepository.findById(id)
            .map(this::convertToDTO)
            .orElse(null);
    }

    public String updateJuniorReportCard(Long id, JuniorReportCardDTO dto) {
        Optional<JuniorReportCard> reportCardOpt = juniorReportCardRepository.findById(id);
        if (reportCardOpt.isEmpty()) {
            return "Report card not found";
        }

        JuniorReportCard reportCard = reportCardOpt.get();
        reportCard.setExamType(dto.getExamType());
        reportCard.setTotalObtainedMarks(dto.getTotalObtainedMarks());
        reportCard.setTotalMaxMarks(dto.getTotalMaxMarks());
        reportCard.setOverallGrade(dto.getOverallGrade());
        reportCard.setClassPosition(dto.getClassPosition());
        reportCard.setPercentage(dto.getPercentage());
        reportCard.setAttendance(dto.getAttendance());
        reportCard.setFinalVerdict(dto.getFinalVerdict());
        reportCard.setIsPassed(dto.getIsPassed());

        juniorReportCardRepository.save(reportCard);
        return "Junior report card updated successfully";
    }

    public String deleteJuniorReportCard(Long id) {
        if (!juniorReportCardRepository.existsById(id)) {
            return "Report card not found";
        }
        juniorReportCardRepository.deleteById(id);
        return "Junior report card deleted successfully";
    }

    private JuniorReportCardDTO convertToDTO(JuniorReportCard reportCard) {
        JuniorReportCardDTO dto = new JuniorReportCardDTO();
        dto.setStudentId(reportCard.getStudent().getId());
        dto.setExamType(reportCard.getExamType());
        dto.setTotalObtainedMarks(reportCard.getTotalObtainedMarks());
        dto.setTotalMaxMarks(reportCard.getTotalMaxMarks());
        dto.setOverallGrade(reportCard.getOverallGrade());
        dto.setClassPosition(reportCard.getClassPosition());
        dto.setPercentage(reportCard.getPercentage());
        dto.setAttendance(reportCard.getAttendance());
        dto.setFinalVerdict(reportCard.getFinalVerdict());
        dto.setIsPassed(reportCard.getIsPassed());
        return dto;
    }
}
