package com.example.trafficpolice.dto;

import java.math.BigDecimal;

public class ViolationTypeRequest {
    private String code;
    private String description;
    private BigDecimal baseFine;
    private double repeatFineIncreasePercent;
    private Integer repeatSuspensionDays;
    private boolean revokeOnRepeat;

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
