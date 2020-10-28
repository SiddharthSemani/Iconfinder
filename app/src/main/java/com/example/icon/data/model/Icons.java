package com.example.icon.data.model;

import com.example.icon.data.model.Prices;
import com.example.icon.data.model.RasterSize;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Icons {
    @SerializedName("raster_sizes")
    @Expose
    private ArrayList<RasterSize> raster_sizes;

    @SerializedName("icon_id")
    @Expose
    private int icon_id;

    @SerializedName("tags")
    @Expose
    private ArrayList<String> tags;

    @SerializedName("is_premium")
    @Expose
    private Boolean is_premium;

    @SerializedName("prices")
    @Expose
    private ArrayList<Prices> prices;

    public Icons(ArrayList<RasterSize> raster_sizes, int icon_id, ArrayList<String> tags, Boolean is_premium, ArrayList<Prices> prices) {
        this.raster_sizes = raster_sizes;
        this.icon_id = icon_id;
        this.tags = tags;
        this.is_premium = is_premium;
        this.prices = prices;
    }

    public ArrayList<Prices> getPrices() {
        return prices;
    }

    public void setPrices(ArrayList<Prices> prices) {
        this.prices = prices;
    }

    public ArrayList<RasterSize> getRaster_sizes() {
        return raster_sizes;
    }

    public void setRaster_sizes(ArrayList<RasterSize> raster_sizes) {
        this.raster_sizes = raster_sizes;
    }

    public int getIcon_id() {
        return icon_id;
    }

    public void setIcon_id(int icon_id) {
        this.icon_id = icon_id;
    }

    public ArrayList<String> getTags() {
        return tags;
    }

    public void setTags(ArrayList<String> tags) {
        this.tags = tags;
    }

    public Boolean getIs_premium() {
        return is_premium;
    }

    public void setIs_premium(Boolean is_premium) {
        this.is_premium = is_premium;
    }

    @Override
    public String toString() {
        return "Icons{" +
                "raster_sizes=" + raster_sizes +
                ", icon_id=" + icon_id +
                ", tags=" + tags +
                ", is_premium=" + is_premium +
                ", prices=" + prices +
                '}';
    }
}
