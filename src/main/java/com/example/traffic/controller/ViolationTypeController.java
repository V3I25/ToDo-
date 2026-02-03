package com.example.traffic.controller;

import com.example.traffic.dto.CreateViolationTypeRequest;
import com.example.traffic.model.ViolationType;
import com.example.traffic.service.ViolationTypeService;
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
@RequestMapping("/api/violation-types")
public class ViolationTypeController {

    private final ViolationTypeService violationTypeService;

    public ViolationTypeController(ViolationTypeService violationTypeService) {
        this.violationTypeService = violationTypeService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ViolationType createViolationType(@Valid @RequestBody CreateViolationTypeRequest request) {
        return violationTypeService.createViolationType(request);
    }

    @GetMapping
    public List<ViolationType> getViolationTypes() {
        return violationTypeService.getAll();
    }
}
