package com.example.trafficpolice.service;

import com.example.trafficpolice.dto.StatisticsResponse;
import com.example.trafficpolice.model.Location;
import com.example.trafficpolice.model.Violation;
import com.example.trafficpolice.repository.ViolationRepository;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class StatisticsService {
    private final ViolationRepository violationRepository;

    public StatisticsService(ViolationRepository violationRepository) {
        this.violationRepository = violationRepository;
    }

    public StatisticsResponse calculateStatistics(
            LocalDateTime start,
            LocalDateTime end,
            Set<String> cities,
            Set<String> districts,
            Set<String> regions
    ) {
        List<Violation> violations = violationRepository.findByViolationDateBetween(start, end);
        List<Violation> filtered = violations.stream()
                .filter(violation -> matchesLocation(violation.getLocation(), cities, districts, regions))
                .toList();

        StatisticsResponse response = new StatisticsResponse();
        response.setTotalViolations(filtered.size());
        response.setTotalFineAmount(sumFine(filtered));
        response.setByType(buildByType(filtered));
        return response;
    }

    private boolean matchesLocation(Location location, Set<String> cities, Set<String> districts, Set<String> regions) {
        if (location == null) {
            return false;
        }
        boolean cityOk = cities.isEmpty() || containsIgnoreCase(cities, location.getCity());
        boolean districtOk = districts.isEmpty() || containsIgnoreCase(districts, location.getDistrict());
        boolean regionOk = regions.isEmpty() || containsIgnoreCase(regions, location.getRegion());
        return cityOk && districtOk && regionOk;
    }

    private boolean containsIgnoreCase(Set<String> candidates, String value) {
        if (value == null) {
            return false;
        }
        return candidates.stream().anyMatch(item -> item.equalsIgnoreCase(value));
    }

    private BigDecimal sumFine(Collection<Violation> violations) {
        return violations.stream()
                .map(Violation::getFineAmount)
                .filter(Objects::nonNull)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private List<StatisticsResponse.ViolationTypeStat> buildByType(List<Violation> violations) {
        Map<Long, StatisticsResponse.ViolationTypeStat> stats = new LinkedHashMap<>();
        for (Violation violation : violations) {
            if (violation.getType() == null) {
                continue;
            }
            stats.computeIfAbsent(violation.getType().getId(), id -> {
                StatisticsResponse.ViolationTypeStat stat = new StatisticsResponse.ViolationTypeStat();
                stat.setTypeCode(violation.getType().getCode());
                stat.setDescription(violation.getType().getDescription());
                return stat;
            });
            StatisticsResponse.ViolationTypeStat stat = stats.get(violation.getType().getId());
            stat.setCount(stat.getCount() + 1);
            BigDecimal fineAmount = violation.getFineAmount() == null ? BigDecimal.ZERO : violation.getFineAmount();
            stat.setTotalFine(stat.getTotalFine().add(fineAmount));
        }
        return stats.values().stream().collect(Collectors.toList());
    }
}
