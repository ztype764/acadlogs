package com.academy.marker.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class JuniorReportCardService {

    @Autowired
    private JuniorReportCardRepository juniorReportCardRepository;

    @Autowired
    private ReportCardConfigService configService;

    public Optional<JuniorReportCard> getReportCardByStudentId(Long studentId) {
        return juniorReportCardRepository.findByStudentId(studentId);
    }

    public JuniorReportCard saveReportCard(JuniorReportCardRequestDTO requestDTO) {
        JuniorReportCard reportCard = new JuniorReportCard();
        reportCard.setStudentId(requestDTO.getStudentId());
        reportCard.setAttendance(requestDTO.getAttendance());

        List<JuniorSubject> subjects = requestDTO.getSubjects().stream().map(dto -> {
            JuniorSubject subject = new JuniorSubject();
            subject.setName(dto.getName());
            subject.setReadingMarks(dto.getReadingMarks());
            subject.setDictationMarks(dto.getDictationMarks());
            subject.setOralMarks(dto.getOralMarks());
            subject.setWritingMarks(dto.getWritingMarks());
            subject.setMaxMarks(dto.getMaxMarks() != null ? dto.getMaxMarks() : configService.getDefaultMaxMarks());
            return subject;
        }).collect(Collectors.toList());

        reportCard.setSubjects(subjects);
        calculateReportCardDetails(reportCard);
        return juniorReportCardRepository.save(reportCard);
    }

    private void calculateReportCardDetails(JuniorReportCard reportCard) {
        int totalMarks = 0;
        int totalMaxMarks = 0;

        for (JuniorSubject subject : reportCard.getSubjects()) {
            if ("Theology".equals(subject.getName())) {
                continue; // Theology is graded, not marked
            }

            int subjectMarks = (subject.getReadingMarks() != null ? subject.getReadingMarks() : 0)
                    + (subject.getDictationMarks() != null ? subject.getDictationMarks() : 0)
                    + (subject.getOralMarks() != null ? subject.getOralMarks() : 0)
                    + (subject.getWritingMarks() != null ? subject.getWritingMarks() : 0);

            totalMarks += subjectMarks;
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
        List<JuniorReportCard> reportCards = juniorReportCardRepository.findAll();

        // Sort by grand total (highest first)
        reportCards.sort((a, b) -> Integer.compare(b.getGrandTotal(), a.getGrandTotal()));

        int rank = 1;
        for (JuniorReportCard reportCard : reportCards) {
            reportCard.setPositionInClass(rank++);
            juniorReportCardRepository.save(reportCard);
        }
    }


    private String calculateGrade(double percentage) {
        if (percentage >= 90) return "A+";
        if (percentage >= 80) return "A";
        if (percentage >= 70) return "B";
        if (percentage >= 60) return "C";
        if (percentage >= 50) return "D";
        return "F";
    }

    public boolean isStudentPassing(JuniorReportCard reportCard) {
        int minTotalMarks = configService.getMinTotalMarks();
        int minPassMarks = configService.getMinPassMarks();

        if (reportCard.getGrandTotal() < minTotalMarks) {
            return false;
        }

        return reportCard.getSubjects().stream()
                .filter(subject -> !"Theology".equals(subject.getName()))
                .allMatch(subject -> {
                    int subjectMarks = (subject.getReadingMarks() != null ? subject.getReadingMarks() : 0)
                            + (subject.getDictationMarks() != null ? subject.getDictationMarks() : 0)
                            + (subject.getOralMarks() != null ? subject.getOralMarks() : 0)
                            + (subject.getWritingMarks() != null ? subject.getWritingMarks() : 0);
                    return subjectMarks >= minPassMarks;
                });
    }
}
