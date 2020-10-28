package com.example.icon.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Prices {
    @SerializedName("currency")
    @Expose
    private String currency;

    @SerializedName("price")
    @Expose
    private String price;

    public Prices(String currency, String price) {
        this.currency = currency;
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Prices{" +
                "currency='" + currency + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
