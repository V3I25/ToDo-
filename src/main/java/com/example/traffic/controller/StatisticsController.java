package com.example.traffic.controller;

import com.example.traffic.dto.StatisticsResponse;
import com.example.traffic.service.StatisticsService;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/statistics")
public class StatisticsController {

    private final StatisticsService statisticsService;

    public StatisticsController(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    @GetMapping
    public StatisticsResponse getStatistics(
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to,
        @RequestParam(required = false) String cities,
        @RequestParam(required = false) String districts,
        @RequestParam(required = false) String regions,
        @RequestParam(defaultValue = "REPUBLIC") String scope
    ) {
        return statisticsService.getStatistics(from, to, parseSet(cities), parseSet(districts), parseSet(regions), scope);
    }

    private Set<String> parseSet(String source) {
        if (source == null || source.isBlank()) {
            return Collections.emptySet();
        }
        return Stream.of(source.split(","))
            .map(String::trim)
            .filter(value -> !value.isBlank())
            .collect(Collectors.toSet());
    }
}
