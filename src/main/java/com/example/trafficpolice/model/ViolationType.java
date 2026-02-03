package com.example.trafficpolice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.math.BigDecimal;

@Entity
public class ViolationType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;
    private String description;
    private BigDecimal baseFine;
    private double repeatFineIncreasePercent;
    private Integer repeatSuspensionDays;
    private boolean revokeOnRepeat;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getBaseFine() {
        return baseFine;
    }

    public void setBaseFine(BigDecimal baseFine) {
        this.baseFine = baseFine;
    }

    public double getRepeatFineIncreasePercent() {
        return repeatFineIncreasePercent;
    }

    public void setRepeatFineIncreasePercent(double repeatFineIncreasePercent) {
        this.repeatFineIncreasePercent = repeatFineIncreasePercent;
    }

    public Integer getRepeatSuspensionDays() {
        return repeatSuspensionDays;
    }

    public void setRepeatSuspensionDays(Integer repeatSuspensionDays) {
        this.repeatSuspensionDays = repeatSuspensionDays;
    }

    public boolean isRevokeOnRepeat() {
        return revokeOnRepeat;
    }

    public void setRevokeOnRepeat(boolean revokeOnRepeat) {
        this.revokeOnRepeat = revokeOnRepeat;
    }
}
