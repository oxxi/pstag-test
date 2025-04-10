package com.pstag.com.model;

import com.pstag.com.core.LocalDateAdapter;
import jakarta.xml.bind.annotation.*;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@XmlRootElement(name = "car")
@XmlAccessorType(XmlAccessType.FIELD)
public class Car {
    private String type;
    private String model;

    @XmlElement(name = "price")
    private Price display;

    @XmlElementWrapper(name = "prices")
    @XmlElement(name = "price")
    private List<Price> prices;

    @XmlElement(required = false)
    private String brand;

    @XmlElement(required = false)
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate releaseDate;

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
    public Price getDisplay() {
        return display;
    }

    public void setDisplay(Price display) {
        this.display = display;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public List<Price> getPrices() {
        return prices;
    }

    public void setPrices(List<Price> prices) {
        this.prices = prices;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Objects.equals(getType(), car.getType()) && Objects.equals(getModel(), car.getModel()) && Objects.equals(getDisplay(), car.getDisplay()) && Objects.equals(getPrices(), car.getPrices()) && Objects.equals(getBrand(), car.getBrand()) && Objects.equals(getReleaseDate(), car.getReleaseDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getType(), getModel(), getDisplay(), getPrices(), getBrand(), getReleaseDate());
    }

    @Override
    public String toString() {
        return "Car{" +
                "brand='" + brand + '\'' +
                ", type='" + type + '\'' +
                ", model='" + model + '\'' +
                ", display=" + display +
                ", prices=" + prices +
                ", releaseDate=" + releaseDate +
                '}';
    }
}

