package com.academy.marker.Services;

import com.academy.marker.DTO.SeniorReportCardDTO;
import com.academy.marker.Repos.SeniorReportCardRepository;
import com.academy.marker.Repos.StudentRepository;
import com.academy.marker.entity.SeniorReportCard;
import com.academy.marker.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SeniorReportCardService {

    @Autowired
    private SeniorReportCardRepository seniorReportCardRepository;

    @Autowired
    private StudentRepository studentRepository;

    public String createSeniorReportCard(SeniorReportCardDTO dto) {
        Optional<Student> studentOpt = studentRepository.findById(dto.getStudentId());
        if (studentOpt.isEmpty()) {
            return "Student not found";
        }

        SeniorReportCard reportCard = new SeniorReportCard();
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

        seniorReportCardRepository.save(reportCard);
        return "Senior report card created successfully";
    }

    public SeniorReportCardDTO getSeniorReportCard(Long id) {
        return seniorReportCardRepository.findById(id)
            .map(this::convertToDTO)
            .orElse(null);
    }

    public String updateSeniorReportCard(Long id, SeniorReportCardDTO dto) {
        Optional<SeniorReportCard> reportCardOpt = seniorReportCardRepository.findById(id);
        if (reportCardOpt.isEmpty()) {
            return "Report card not found";
        }

        SeniorReportCard reportCard = reportCardOpt.get();
        reportCard.setExamType(dto.getExamType());
        reportCard.setTotalObtainedMarks(dto.getTotalObtainedMarks());
        reportCard.setTotalMaxMarks(dto.getTotalMaxMarks());
        reportCard.setOverallGrade(dto.getOverallGrade());
        reportCard.setClassPosition(dto.getClassPosition());
        reportCard.setPercentage(dto.getPercentage());
        reportCard.setAttendance(dto.getAttendance());
        reportCard.setFinalVerdict(dto.getFinalVerdict());
        reportCard.setIsPassed(dto.getIsPassed());

        seniorReportCardRepository.save(reportCard);
        return "Senior report card updated successfully";
    }

    public String deleteSeniorReportCard(Long id) {
        if (!seniorReportCardRepository.existsById(id)) {
            return "Report card not found";
        }
        seniorReportCardRepository.deleteById(id);
        return "Senior report card deleted successfully";
    }

    private SeniorReportCardDTO convertToDTO(SeniorReportCard reportCard) {
        SeniorReportCardDTO dto = new SeniorReportCardDTO();
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
