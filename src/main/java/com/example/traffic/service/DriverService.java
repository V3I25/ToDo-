package com.example.traffic.service;

import com.example.traffic.dto.CreateDriverRequest;
import com.example.traffic.model.Driver;
import com.example.traffic.model.DriverLicense;
import com.example.traffic.repository.DriverRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DriverService {

    private final DriverRepository driverRepository;

    public DriverService(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    @Transactional
    public Driver createDriver(CreateDriverRequest request) {
        Driver driver = new Driver();
        driver.setFullName(request.getFullName());
        driver.setBirthDate(request.getBirthDate());

        DriverLicense license = new DriverLicense();
        license.setLicenseNumber(request.getLicenseNumber());
        license.setIssuedDate(request.getLicenseIssuedDate());
        license.setExpirationDate(request.getLicenseExpirationDate());

        driver.setLicense(license);
        return driverRepository.save(driver);
    }

    public List<Driver> getAllDrivers() {
        return driverRepository.findAll();
    }

    public Driver getDriver(Long id) {
        return driverRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Driver not found"));
    }
}
