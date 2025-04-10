package com.pstag.com.model;

import java.time.LocalDate;
import java.util.Objects;

public class CarBrand {
    private String brand;
    private LocalDate releaseDate;

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CarBrand cardBrand = (CarBrand) o;
        return Objects.equals(getBrand(), cardBrand.getBrand()) && Objects.equals(getReleaseDate(), cardBrand.getReleaseDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getBrand(), getReleaseDate());
    }
}
