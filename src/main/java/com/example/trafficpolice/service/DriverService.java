package com.example.trafficpolice.service;

import com.example.trafficpolice.dto.DriverRequest;
import com.example.trafficpolice.model.Driver;
import com.example.trafficpolice.model.DriverLicense;
import com.example.trafficpolice.repository.DriverRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class DriverService {
    private final DriverRepository driverRepository;

    public DriverService(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    public Driver createDriver(DriverRequest request) {
        Driver driver = new Driver();
        driver.setFullName(request.getFullName());
        driver.setBirthDate(request.getBirthDate());

        DriverLicense license = new DriverLicense();
        license.setLicenseNumber(request.getLicenseNumber());
        license.setIssuedDate(request.getIssuedDate());
        license.setExpiryDate(request.getExpiryDate());
        driver.setLicense(license);

        return driverRepository.save(driver);
    }

    public List<Driver> listDrivers() {
        return driverRepository.findAll();
    }
}
