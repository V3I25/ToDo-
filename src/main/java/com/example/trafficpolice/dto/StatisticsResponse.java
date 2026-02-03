package com.example.trafficpolice.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class StatisticsResponse {
    private long totalViolations;
    private BigDecimal totalFineAmount = BigDecimal.ZERO;
    private List<ViolationTypeStat> byType = new ArrayList<>();

    public long getTotalViolations() {
        return totalViolations;
    }

    public void setTotalViolations(long totalViolations) {
        this.totalViolations = totalViolations;
    }

    public BigDecimal getTotalFineAmount() {
        return totalFineAmount;
    }

    public void setTotalFineAmount(BigDecimal totalFineAmount) {
        this.totalFineAmount = totalFineAmount;
    }

    public List<ViolationTypeStat> getByType() {
        return byType;
    }

    public void setByType(List<ViolationTypeStat> byType) {
        this.byType = byType;
    }

    public static class ViolationTypeStat {
        private String typeCode;
        private String description;
        private long count;
        private BigDecimal totalFine = BigDecimal.ZERO;

        public String getTypeCode() {
            return typeCode;
        }

        public void setTypeCode(String typeCode) {
            this.typeCode = typeCode;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public long getCount() {
            return count;
        }

        public void setCount(long count) {
            this.count = count;
        }

        public BigDecimal getTotalFine() {
            return totalFine;
        }

        public void setTotalFine(BigDecimal totalFine) {
            this.totalFine = totalFine;
        }
    }
}
