package com.academy.marker.Repos;

import com.academy.marker.entity.SeniorReportCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SeniorReportCardRepository extends JpaRepository<SeniorReportCard, Long> {

    Optional<SeniorReportCard> findByStudentId(Long studentId);
}
