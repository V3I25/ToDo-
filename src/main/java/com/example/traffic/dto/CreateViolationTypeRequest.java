package com.example.traffic.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CreateViolationTypeRequest {

    @NotBlank
    private String articleCode;

    @NotBlank
    private String description;

    @Min(0)
    private double baseFineAmount;

    @Min(0)
    private double repeatFineIncreasePercent;

    private Integer suspensionDaysFirst;

    private Integer suspensionDaysRepeat;

    @NotNull
    private Boolean revokeOnRepeat;

    public String getArticleCode() {
        return articleCode;
    }

    public void setArticleCode(String articleCode) {
        this.articleCode = articleCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getBaseFineAmount() {
        return baseFineAmount;
    }

    public void setBaseFineAmount(double baseFineAmount) {
        this.baseFineAmount = baseFineAmount;
    }

    public double getRepeatFineIncreasePercent() {
        return repeatFineIncreasePercent;
    }

    public void setRepeatFineIncreasePercent(double repeatFineIncreasePercent) {
        this.repeatFineIncreasePercent = repeatFineIncreasePercent;
    }

    public Integer getSuspensionDaysFirst() {
        return suspensionDaysFirst;
    }

    public void setSuspensionDaysFirst(Integer suspensionDaysFirst) {
        this.suspensionDaysFirst = suspensionDaysFirst;
    }

    public Integer getSuspensionDaysRepeat() {
        return suspensionDaysRepeat;
    }

    public void setSuspensionDaysRepeat(Integer suspensionDaysRepeat) {
        this.suspensionDaysRepeat = suspensionDaysRepeat;
    }

    public Boolean getRevokeOnRepeat() {
        return revokeOnRepeat;
    }

    public void setRevokeOnRepeat(Boolean revokeOnRepeat) {
        this.revokeOnRepeat = revokeOnRepeat;
    }
}
