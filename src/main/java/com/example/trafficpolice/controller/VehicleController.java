package com.example.trafficpolice.controller;

import com.example.trafficpolice.dto.VehicleRequest;
import com.example.trafficpolice.model.Vehicle;
import com.example.trafficpolice.service.VehicleService;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {
    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @PostMapping
    public Vehicle createVehicle(@RequestBody VehicleRequest request) {
        return vehicleService.createVehicle(request);
    }

    @GetMapping
    public List<Vehicle> listVehicles() {
        return vehicleService.listVehicles();
    }
}
