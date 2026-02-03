package com.example.trafficpolice.service;

import com.example.trafficpolice.dto.ViolationTypeRequest;
import com.example.trafficpolice.model.ViolationType;
import com.example.trafficpolice.repository.ViolationTypeRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ViolationTypeService {
    private final ViolationTypeRepository violationTypeRepository;

    public ViolationTypeService(ViolationTypeRepository violationTypeRepository) {
        this.violationTypeRepository = violationTypeRepository;
    }

    public ViolationType createViolationType(ViolationTypeRequest request) {
        ViolationType type = new ViolationType();
        type.setCode(request.getCode());
        type.setDescription(request.getDescription());
        type.setBaseFine(request.getBaseFine());
        type.setRepeatFineIncreasePercent(request.getRepeatFineIncreasePercent());
        type.setRepeatSuspensionDays(request.getRepeatSuspensionDays());
        type.setRevokeOnRepeat(request.isRevokeOnRepeat());
        return violationTypeRepository.save(type);
    }

    public List<ViolationType> listViolationTypes() {
        return violationTypeRepository.findAll();
    }
}
