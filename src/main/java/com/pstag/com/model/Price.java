package com.pstag.com.model;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlValue;

import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class Price {
    @XmlValue
    private Double price;
    @XmlAttribute
    private String currency;

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Price price1 = (Price) o;
        return Objects.equals(getPrice(), price1.getPrice()) && Objects.equals(getCurrency(), price1.getCurrency());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPrice(), getCurrency());
    }

    @Override
    public String toString() {
        return "Price{" +
                "currency='" + currency + '\'' +
                ", price=" + price +
                '}';
    }
}
