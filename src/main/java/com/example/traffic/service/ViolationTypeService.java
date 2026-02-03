package com.example.traffic.service;

import com.example.traffic.dto.CreateViolationTypeRequest;
import com.example.traffic.model.ViolationType;
import com.example.traffic.repository.ViolationTypeRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ViolationTypeService {

    private final ViolationTypeRepository violationTypeRepository;

    public ViolationTypeService(ViolationTypeRepository violationTypeRepository) {
        this.violationTypeRepository = violationTypeRepository;
    }

    public ViolationType createViolationType(CreateViolationTypeRequest request) {
        ViolationType type = new ViolationType();
        type.setArticleCode(request.getArticleCode());
        type.setDescription(request.getDescription());
        type.setBaseFineAmount(request.getBaseFineAmount());
        type.setRepeatFineIncreasePercent(request.getRepeatFineIncreasePercent());
        type.setSuspensionDaysFirst(request.getSuspensionDaysFirst());
        type.setSuspensionDaysRepeat(request.getSuspensionDaysRepeat());
        type.setRevokeOnRepeat(Boolean.TRUE.equals(request.getRevokeOnRepeat()));
        return violationTypeRepository.save(type);
    }

    public List<ViolationType> getAll() {
        return violationTypeRepository.findAll();
    }

    public ViolationType getById(Long id) {
        return violationTypeRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Violation type not found"));
    }
}
