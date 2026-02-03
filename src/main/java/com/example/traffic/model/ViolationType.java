package com.example.traffic.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ViolationType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String articleCode;

    private String description;

    private double baseFineAmount;

    private double repeatFineIncreasePercent;

    private Integer suspensionDaysFirst;

    private Integer suspensionDaysRepeat;

    private boolean revokeOnRepeat;

    public Long getId() {
        return id;
    }

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

    public boolean isRevokeOnRepeat() {
        return revokeOnRepeat;
    }

    public void setRevokeOnRepeat(boolean revokeOnRepeat) {
        this.revokeOnRepeat = revokeOnRepeat;
    }
}
