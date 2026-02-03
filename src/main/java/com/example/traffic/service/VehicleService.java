package com.example.traffic.service;

import com.example.traffic.dto.CreateVehicleRequest;
import com.example.traffic.model.Driver;
import com.example.traffic.model.Vehicle;
import com.example.traffic.repository.DriverRepository;
import com.example.traffic.repository.VehicleRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VehicleService {

    private final VehicleRepository vehicleRepository;
    private final DriverRepository driverRepository;

    public VehicleService(VehicleRepository vehicleRepository, DriverRepository driverRepository) {
        this.vehicleRepository = vehicleRepository;
        this.driverRepository = driverRepository;
    }

    @Transactional
    public Vehicle createVehicle(CreateVehicleRequest request) {
        Driver driver = driverRepository.findById(request.getDriverId())
            .orElseThrow(() -> new IllegalArgumentException("Driver not found"));

        Vehicle vehicle = new Vehicle();
        vehicle.setOwner(driver);
        vehicle.setPlateNumber(request.getPlateNumber());
        vehicle.setModel(request.getModel());
        vehicle.setVin(request.getVin());

        return vehicleRepository.save(vehicle);
    }

    public List<Vehicle> getVehicles() {
        return vehicleRepository.findAll();
    }
}
