package com.example.seebook.domain.report.repository;

import com.example.seebook.domain.report.domain.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long>, ReportRepositoryCustom {
}
