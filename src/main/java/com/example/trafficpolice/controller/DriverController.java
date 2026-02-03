package com.example.trafficpolice.controller;

import com.example.trafficpolice.dto.DriverRequest;
import com.example.trafficpolice.model.Driver;
import com.example.trafficpolice.service.DriverService;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/drivers")
public class DriverController {
    private final DriverService driverService;

    public DriverController(DriverService driverService) {
        this.driverService = driverService;
    }

    @PostMapping
    public Driver createDriver(@RequestBody DriverRequest request) {
        return driverService.createDriver(request);
    }

    @GetMapping
    public List<Driver> listDrivers() {
        return driverService.listDrivers();
    }
}
