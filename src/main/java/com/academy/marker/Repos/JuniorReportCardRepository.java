package com.academy.marker.Repos;


import com.academy.marker.entity.JuniorReportCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JuniorReportCardRepository extends JpaRepository<JuniorReportCard, Long> {

    Optional<JuniorReportCard> findByStudentId(Long studentId);
}
