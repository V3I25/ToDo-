package com.example.traffic.service;

import com.example.traffic.dto.CreateViolationRequest;
import com.example.traffic.model.Driver;
import com.example.traffic.model.DriverLicense;
import com.example.traffic.model.LicenseStatus;
import com.example.traffic.model.Vehicle;
import com.example.traffic.model.Violation;
import com.example.traffic.model.ViolationType;
import com.example.traffic.repository.DriverRepository;
import com.example.traffic.repository.VehicleRepository;
import com.example.traffic.repository.ViolationRepository;
import com.example.traffic.repository.ViolationTypeRepository;
import java.time.LocalDate;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ViolationService {

    private final ViolationRepository violationRepository;
    private final DriverRepository driverRepository;
    private final VehicleRepository vehicleRepository;
    private final ViolationTypeRepository violationTypeRepository;

    public ViolationService(ViolationRepository violationRepository,
                            DriverRepository driverRepository,
                            VehicleRepository vehicleRepository,
                            ViolationTypeRepository violationTypeRepository) {
        this.violationRepository = violationRepository;
        this.driverRepository = driverRepository;
        this.vehicleRepository = vehicleRepository;
        this.violationTypeRepository = violationTypeRepository;
    }

    @Transactional
    public Violation registerViolation(CreateViolationRequest request) {
        Driver driver = driverRepository.findById(request.getDriverId())
            .orElseThrow(() -> new IllegalArgumentException("Driver not found"));
        Vehicle vehicle = vehicleRepository.findById(request.getVehicleId())
            .orElseThrow(() -> new IllegalArgumentException("Vehicle not found"));
        ViolationType type = violationTypeRepository.findById(request.getViolationTypeId())
            .orElseThrow(() -> new IllegalArgumentException("Violation type not found"));

        long previousCount = violationRepository
            .countByDriverIdAndViolationTypeIdAndViolationDateBefore(driver.getId(), type.getId(), request.getViolationDate());
        boolean isRepeat = previousCount > 0;

        double fineAmount = type.getBaseFineAmount();
        if (isRepeat && type.getRepeatFineIncreasePercent() > 0) {
            fineAmount = fineAmount * (1 + type.getRepeatFineIncreasePercent() / 100.0);
        }

        applyLicensePenalty(driver.getLicense(), type, isRepeat, request.getViolationDate());

        Violation violation = new Violation();
        violation.setDriver(driver);
        violation.setVehicle(vehicle);
        violation.setViolationType(type);
        violation.setViolationDate(request.getViolationDate());
        violation.setCity(request.getCity());
        violation.setDistrict(request.getDistrict());
        violation.setRegion(request.getRegion());
        violation.setFineAmount(fineAmount);
        violation.setRepeatViolation(isRepeat);

        return violationRepository.save(violation);
    }

    public List<Violation> getAllViolations() {
        return violationRepository.findAll();
    }

    private void applyLicensePenalty(DriverLicense license, ViolationType type, boolean repeat, LocalDate violationDate) {
        if (license == null) {
            return;
        }

        if (repeat && type.isRevokeOnRepeat()) {
            license.setStatus(LicenseStatus.REVOKED);
            license.setSuspendedUntil(null);
            return;
        }

        Integer suspensionDays = repeat ? type.getSuspensionDaysRepeat() : type.getSuspensionDaysFirst();
        if (suspensionDays != null && suspensionDays > 0) {
            license.setStatus(LicenseStatus.SUSPENDED);
            license.setSuspendedUntil(violationDate.plusDays(suspensionDays));
        }
    }
}
