package com.academy.marker.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SeniorReportCardService {

    @Autowired
    private SeniorReportCardRepository seniorReportCardRepository;

    @Autowired
    private ReportCardConfigService configService;

    public Optional<SeniorReportCard> getReportCardByStudentId(Long studentId) {
        return seniorReportCardRepository.findByStudentId(studentId);
    }

    public SeniorReportCard saveReportCard(SeniorReportCardRequestDTO requestDTO) {
        SeniorReportCard reportCard = new SeniorReportCard();
        reportCard.setStudentId(requestDTO.getStudentId());
        reportCard.setAttendance(requestDTO.getAttendance());

        List<SeniorSubject> subjects = requestDTO.getSubjects().stream().map(dto -> {
            SeniorSubject subject = new SeniorSubject();
            subject.setName(dto.getName());
            subject.setMarks(dto.getMarks());
            subject.setMaxMarks(dto.getMaxMarks() != null ? dto.getMaxMarks() : configService.getDefaultMaxMarks());
            return subject;
        }).collect(Collectors.toList());

        reportCard.setSubjects(subjects);
        calculateReportCardDetails(reportCard);
        return seniorReportCardRepository.save(reportCard);
    }

    private void calculateReportCardDetails(SeniorReportCard reportCard) {
        int totalMarks = 0;
        int totalMaxMarks = 0;

        for (SeniorSubject subject : reportCard.getSubjects()) {
            totalMarks += subject.getMarks();
            totalMaxMarks += subject.getMaxMarks() != null ? subject.getMaxMarks() : configService.getDefaultMaxMarks();
        }

        double percentage = (totalMarks * 100.0) / totalMaxMarks;
        String grade = calculateGrade(percentage);
        boolean isPassing = isStudentPassing(reportCard);

        reportCard.setGrandTotal(totalMarks);
        reportCard.setPercentage(percentage);
        reportCard.setOverallGrade(grade);
        reportCard.setFinalVerdict(isPassing ? "Pass" : "Fail");
    }

    public void updatePositionsInClass() {
        List<SeniorReportCard> reportCards = seniorReportCardRepository.findAll();

        // Sort by grand total (highest first)
        reportCards.sort((a, b) -> Integer.compare(b.getGrandTotal(), a.getGrandTotal()));

        int rank = 1;
        for (SeniorReportCard reportCard : reportCards) {
            reportCard.setPositionInClass(rank++);
            seniorReportCardRepository.save(reportCard);
        }
    }


    public boolean isStudentPassing(SeniorReportCard reportCard) {
        return reportCard.getGrandTotal() >= configService.getMinTotalMarks()
                && reportCard.getSubjects().stream()
                .allMatch(subject -> subject.getMarks() >= configService.getMinPassMarks());
    }
}
