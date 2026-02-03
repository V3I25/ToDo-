package com.example.trafficpolice.model;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class Violation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Driver driver;

    @ManyToOne
    private Vehicle vehicle;

    @ManyToOne
    private ViolationType type;

    private LocalDateTime violationDate;

    @Embedded
    private Location location;

    private BigDecimal fineAmount;
    private boolean repeatOffense;

    @Enumerated(EnumType.STRING)
    private LicenseAction licenseAction = LicenseAction.NONE;

    private Integer suspensionDays;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public ViolationType getType() {
        return type;
    }

    public void setType(ViolationType type) {
        this.type = type;
    }

    public LocalDateTime getViolationDate() {
        return violationDate;
    }

    public void setViolationDate(LocalDateTime violationDate) {
        this.violationDate = violationDate;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
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
}
