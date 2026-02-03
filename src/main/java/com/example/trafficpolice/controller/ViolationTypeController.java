package com.example.trafficpolice.controller;

import com.example.trafficpolice.dto.ViolationTypeRequest;
import com.example.trafficpolice.model.ViolationType;
import com.example.trafficpolice.service.ViolationTypeService;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/violation-types")
public class ViolationTypeController {
    private final ViolationTypeService violationTypeService;

    public ViolationTypeController(ViolationTypeService violationTypeService) {
        this.violationTypeService = violationTypeService;
    }

    @PostMapping
    public ViolationType createType(@RequestBody ViolationTypeRequest request) {
        return violationTypeService.createViolationType(request);
    }

    @GetMapping
    public List<ViolationType> listTypes() {
        return violationTypeService.listViolationTypes();
    }
}
