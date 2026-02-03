package com.example.traffic.controller;

import com.example.traffic.dto.CreateViolationRequest;
import com.example.traffic.model.Violation;
import com.example.traffic.service.ViolationService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/violations")
public class ViolationController {

    private final ViolationService violationService;

    public ViolationController(ViolationService violationService) {
        this.violationService = violationService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Violation registerViolation(@Valid @RequestBody CreateViolationRequest request) {
        return violationService.registerViolation(request);
    }

    @GetMapping
    public List<Violation> getViolations() {
        return violationService.getAllViolations();
    }
}
