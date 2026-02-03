package com.example.trafficpolice.dto;

import com.example.trafficpolice.model.LicenseAction;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ViolationResponse {
    private Long id;
    private String violationType;
    private BigDecimal fineAmount;
    private boolean repeatOffense;
    private LicenseAction licenseAction;
    private Integer suspensionDays;
    private LocalDateTime violationDate;
    private String city;
    private String district;
    private String region;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getViolationType() {
        return violationType;
    }

    public void setViolationType(String violationType) {
        this.violationType = violationType;
    }

    public BigDecimal getFineAmount() {
        return fineAmount;
    }

    public void setFineAmount(BigDecimal fineAmount) {
        this.fineAmount = fineAmount;
    }

    public boolean isRepeatOffense() {
        return repeatOffense;
    }

    public void setRepeatOffense(boolean repeatOffense) {
        this.repeatOffense = repeatOffense;
    }

    public LicenseAction getLicenseAction() {
        return licenseAction;
    }

    public void setLicenseAction(LicenseAction licenseAction) {
        this.licenseAction = licenseAction;
    }

    public Integer getSuspensionDays() {
        return suspensionDays;
    }

    public void setSuspensionDays(Integer suspensionDays) {
        this.suspensionDays = suspensionDays;
    }

    public LocalDateTime getViolationDate() {
        return violationDate;
    }

    public void setViolationDate(LocalDateTime violationDate) {
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
