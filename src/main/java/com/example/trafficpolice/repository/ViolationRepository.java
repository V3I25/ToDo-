package com.example.trafficpolice.repository;

import com.example.trafficpolice.model.Driver;
import com.example.trafficpolice.model.Violation;
import com.example.trafficpolice.model.ViolationType;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ViolationRepository extends JpaRepository<Violation, Long> {
    long countByDriverAndType(Driver driver, ViolationType type);

    List<Violation> findByViolationDateBetween(LocalDateTime start, LocalDateTime end);
}
