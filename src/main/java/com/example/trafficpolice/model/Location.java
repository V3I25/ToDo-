package com.example.trafficpolice.model;

import jakarta.persistence.Embeddable;

@Embeddable
public class Location {
    private String city;
    private String district;
    private String region;

    public Location() {
    }

    public Location(String city, String district, String region) {
        this.city = city;
        this.district = district;
        this.region = region;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }
}
