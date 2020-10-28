package com.example.icon.data.model;

import com.example.icon.data.model.Icons;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Icon {
    @SerializedName("total_count")
    @Expose
    private int total_count;

    @SerializedName("icons")
    @Expose
    private ArrayList<Icons> icons;

    public int getTotal_count() {
        return total_count;
    }

    public void setTotal_count(int total_count) {
        this.total_count = total_count;
    }

    public ArrayList<Icons> getIcons() {
        return icons;
    }

    public void setIcons(ArrayList<Icons> icons) {
        this.icons = icons;
    }

    public Icon(int total_count, ArrayList<Icons> icons) {
        this.total_count = total_count;
        this.icons = icons;
    }

    @Override
    public String toString() {
        return "Icon{" +
                "total_count=" + total_count +
                ", icons=" + icons +
                '}';
    }
}
