package com.example.icon.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Iconsets {
    public Iconsets(String name, String iconset_id, ArrayList<Price> prices, Boolean is_premium) {
        this.name = name;
        this.iconset_id = iconset_id;
        this.prices = prices;
        this.is_premium = is_premium;
    }

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("iconset_id")
    @Expose
    private String iconset_id;

    @SerializedName("prices")
    @Expose
    private ArrayList<Price> prices;

    @SerializedName("is_premium")
    @Expose
    private Boolean is_premium;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIconset_id() {
        return iconset_id;
    }

    public void setIconset_id(String iconset_id) {
        this.iconset_id = iconset_id;
    }

    public ArrayList<Price> getPrices() {
        return prices;
    }

    public void setPrices(ArrayList<Price> prices) {
        this.prices = prices;
    }

    public Boolean getIs_premium() {
        return is_premium;
    }

    public void setIs_premium(Boolean is_premium) {
        this.is_premium = is_premium;
    }

    @Override
    public String toString() {
        return "Iconsets{" +
                "name='" + name + '\'' +
                ", iconset_id='" + iconset_id + '\'' +
                ", prices=" + prices +
                ", is_premium=" + is_premium +
                '}';
    }
}
