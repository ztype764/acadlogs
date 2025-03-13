package com.academy.marker.Repos;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportCardConfigRepository extends JpaRepository<ReportCardConfig, Long> {
}
