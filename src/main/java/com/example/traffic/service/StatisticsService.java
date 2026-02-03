package com.example.traffic.service;

import com.example.traffic.dto.StatisticsResponse;
import com.example.traffic.model.Violation;
import com.example.traffic.repository.ViolationRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class StatisticsService {

    private final ViolationRepository violationRepository;

    public StatisticsService(ViolationRepository violationRepository) {
        this.violationRepository = violationRepository;
    }

    public StatisticsResponse getStatistics(LocalDate from,
                                            LocalDate to,
                                            Set<String> cities,
                                            Set<String> districts,
                                            Set<String> regions,
                                            String scopeLabel) {
        List<Violation> violations = violationRepository.findAll();

        Predicate<Violation> datePredicate = violation -> !violation.getViolationDate().isBefore(from)
            && !violation.getViolationDate().isAfter(to);

        Predicate<Violation> cityPredicate = violation -> cities.isEmpty() || cities.contains(violation.getCity());
        Predicate<Violation> districtPredicate = violation -> districts.isEmpty() || districts.contains(violation.getDistrict());
        Predicate<Violation> regionPredicate = violation -> regions.isEmpty() || regions.contains(violation.getRegion());

        List<Violation> filtered = violations.stream()
            .filter(datePredicate)
            .filter(cityPredicate)
            .filter(districtPredicate)
            .filter(regionPredicate)
            .toList();

        long totalViolations = filtered.size();
        double totalFinesAmount = filtered.stream().mapToDouble(Violation::getFineAmount).sum();
        Map<String, Long> violationsByType = filtered.stream()
            .collect(Collectors.groupingBy(violation -> violation.getViolationType().getArticleCode(), Collectors.counting()));

        return new StatisticsResponse(scopeLabel, totalViolations, totalFinesAmount, violationsByType);
    }
}
