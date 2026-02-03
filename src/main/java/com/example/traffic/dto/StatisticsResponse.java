package com.example.traffic.dto;

import java.util.Map;

public class StatisticsResponse {

    private String scope;
    private long totalViolations;
    private double totalFinesAmount;
    private Map<String, Long> violationsByType;

    public StatisticsResponse(String scope, long totalViolations, double totalFinesAmount, Map<String, Long> violationsByType) {
        this.scope = scope;
        this.totalViolations = totalViolations;
        this.totalFinesAmount = totalFinesAmount;
        this.violationsByType = violationsByType;
    }

    public String getScope() {
        return scope;
    }

    public long getTotalViolations() {
        return totalViolations;
    }

    public double getTotalFinesAmount() {
        return totalFinesAmount;
    }

    public Map<String, Long> getViolationsByType() {
        return violationsByType;
    }
}
