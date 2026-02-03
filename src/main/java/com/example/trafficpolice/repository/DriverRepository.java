package com.example.trafficpolice.repository;

import com.example.trafficpolice.model.Driver;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DriverRepository extends JpaRepository<Driver, Long> {
}
