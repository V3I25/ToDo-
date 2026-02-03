package com.example.traffic.repository;

import com.example.traffic.model.Violation;
import java.time.LocalDate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ViolationRepository extends JpaRepository<Violation, Long> {

    long countByDriverIdAndViolationTypeIdAndViolationDateBefore(Long driverId, Long violationTypeId, LocalDate violationDate);
}
