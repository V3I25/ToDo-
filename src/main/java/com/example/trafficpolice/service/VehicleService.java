package com.example.trafficpolice.service;

import com.example.trafficpolice.dto.VehicleRequest;
import com.example.trafficpolice.model.Driver;
import com.example.trafficpolice.model.Vehicle;
import com.example.trafficpolice.repository.DriverRepository;
import com.example.trafficpolice.repository.VehicleRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class VehicleService {
    private final VehicleRepository vehicleRepository;
    private final DriverRepository driverRepository;

    public VehicleService(VehicleRepository vehicleRepository, DriverRepository driverRepository) {
        this.vehicleRepository = vehicleRepository;
        this.driverRepository = driverRepository;
    }

    public Vehicle createVehicle(VehicleRequest request) {
        Driver owner = driverRepository.findById(request.getOwnerId())
                .orElseThrow(() -> new IllegalArgumentException("Driver not found"));

        Vehicle vehicle = new Vehicle();
        vehicle.setOwner(owner);
        vehicle.setModel(request.getModel());
        vehicle.setPlateNumber(request.getPlateNumber());

        return vehicleRepository.save(vehicle);
    }

    public List<Vehicle> listVehicles() {
        return vehicleRepository.findAll();
    }
}
