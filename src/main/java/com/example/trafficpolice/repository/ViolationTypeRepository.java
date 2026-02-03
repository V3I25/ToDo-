package com.example.trafficpolice.repository;

import com.example.trafficpolice.model.ViolationType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ViolationTypeRepository extends JpaRepository<ViolationType, Long> {
}
