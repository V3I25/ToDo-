package com.example.trafficpolice.service;

import com.example.trafficpolice.dto.ViolationRequest;
import com.example.trafficpolice.dto.ViolationResponse;
import com.example.trafficpolice.model.Driver;
import com.example.trafficpolice.model.DriverLicense;
import com.example.trafficpolice.model.LicenseAction;
import com.example.trafficpolice.model.LicenseStatus;
import com.example.trafficpolice.model.Location;
import com.example.trafficpolice.model.Vehicle;
import com.example.trafficpolice.model.Violation;
import com.example.trafficpolice.model.ViolationType;
import com.example.trafficpolice.repository.DriverRepository;
import com.example.trafficpolice.repository.VehicleRepository;
import com.example.trafficpolice.repository.ViolationRepository;
import com.example.trafficpolice.repository.ViolationTypeRepository;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import org.springframework.stereotype.Service;

@Service
public class ViolationService {
    private final ViolationRepository violationRepository;
    private final DriverRepository driverRepository;
    private final VehicleRepository vehicleRepository;
    private final ViolationTypeRepository violationTypeRepository;

    public ViolationService(
            ViolationRepository violationRepository,
            DriverRepository driverRepository,
            VehicleRepository vehicleRepository,
            ViolationTypeRepository violationTypeRepository
    ) {
        this.violationRepository = violationRepository;
        this.driverRepository = driverRepository;
        this.vehicleRepository = vehicleRepository;
        this.violationTypeRepository = violationTypeRepository;
    }

    public ViolationResponse recordViolation(ViolationRequest request) {
        Driver driver = driverRepository.findById(request.getDriverId())
                .orElseThrow(() -> new IllegalArgumentException("Driver not found"));
        Vehicle vehicle = vehicleRepository.findById(request.getVehicleId())
                .orElseThrow(() -> new IllegalArgumentException("Vehicle not found"));
        ViolationType type = violationTypeRepository.findById(request.getViolationTypeId())
                .orElseThrow(() -> new IllegalArgumentException("Violation type not found"));

        long previousCount = violationRepository.countByDriverAndType(driver, type);
        boolean repeat = previousCount > 0;

        BigDecimal fineAmount = type.getBaseFine();
        if (repeat && type.getRepeatFineIncreasePercent() > 0) {
            BigDecimal multiplier = BigDecimal.valueOf(1 + type.getRepeatFineIncreasePercent() / 100.0);
            fineAmount = fineAmount.multiply(multiplier).setScale(2, RoundingMode.HALF_UP);
        }

        Violation violation = new Violation();
        violation.setDriver(driver);
        violation.setVehicle(vehicle);
        violation.setType(type);
        violation.setViolationDate(request.getViolationDate() != null ? request.getViolationDate() : LocalDateTime.now());
        violation.setLocation(new Location(request.getCity(), request.getDistrict(), request.getRegion()));
        violation.setFineAmount(fineAmount);
        violation.setRepeatOffense(repeat);

        applyLicenseAction(driver.getLicense(), type, violation, repeat);

        Violation saved = violationRepository.save(violation);
        driverRepository.save(driver);

        return toResponse(saved);
    }

    private void applyLicenseAction(DriverLicense license, ViolationType type, Violation violation, boolean repeat) {
        if (!repeat || license == null) {
            return;
        }
        if (type.isRevokeOnRepeat()) {
            license.setStatus(LicenseStatus.REVOKED);
            license.setSuspendedUntil(null);
            violation.setLicenseAction(LicenseAction.REVOKE);
            return;
        }
        if (type.getRepeatSuspensionDays() != null && type.getRepeatSuspensionDays() > 0) {
            license.setStatus(LicenseStatus.SUSPENDED);
            LocalDate suspendedUntil = LocalDate.now().plusDays(type.getRepeatSuspensionDays());
            license.setSuspendedUntil(suspendedUntil);
            violation.setLicenseAction(LicenseAction.SUSPEND);
            violation.setSuspensionDays(type.getRepeatSuspensionDays());
        }
    }

    private ViolationResponse toResponse(Violation violation) {
        ViolationResponse response = new ViolationResponse();
        response.setId(violation.getId());
        response.setViolationType(violation.getType().getDescription());
        response.setFineAmount(violation.getFineAmount());
        response.setRepeatOffense(violation.isRepeatOffense());
        response.setLicenseAction(violation.getLicenseAction());
        response.setSuspensionDays(violation.getSuspensionDays());
        response.setViolationDate(violation.getViolationDate());
        if (violation.getLocation() != null) {
            response.setCity(violation.getLocation().getCity());
            response.setDistrict(violation.getLocation().getDistrict());
            response.setRegion(violation.getLocation().getRegion());
        }
        return response;
    }
}
