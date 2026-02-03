package com.example.traffic.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public class CreateViolationRequest {

    @NotNull
    private Long driverId;

    @NotNull
    private Long vehicleId;

    @NotNull
    private Long violationTypeId;

    @NotNull
    private LocalDate violationDate;

    @NotBlank
    private String city;

    @NotBlank
    private String district;

    @NotBlank
    private String region;

    public Long getDriverId() {
        return driverId;
    }

    public void setDriverId(Long driverId) {
        this.driverId = driverId;
    }

    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public Long getViolationTypeId() {
        return violationTypeId;
    }

    public void setViolationTypeId(Long violationTypeId) {
        this.violationTypeId = violationTypeId;
    }

    public LocalDate getViolationDate() {
        return violationDate;
    }

    public void setViolationDate(LocalDate violationDate) {
        this.violationDate = violationDate;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }
}
