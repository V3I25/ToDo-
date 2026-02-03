package com.example.trafficpolice.controller;

import com.example.trafficpolice.dto.ViolationRequest;
import com.example.trafficpolice.dto.ViolationResponse;
import com.example.trafficpolice.service.ViolationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/violations")
public class ViolationController {
    private final ViolationService violationService;

    public ViolationController(ViolationService violationService) {
        this.violationService = violationService;
    }

    @PostMapping
    public ViolationResponse createViolation(@RequestBody ViolationRequest request) {
        return violationService.recordViolation(request);
    }
}
