package com.example.icon.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class IconsetObject {
    @SerializedName("iconsets")
    @Expose
    private ArrayList<Iconsets> iconsets;

    public IconsetObject(ArrayList<Iconsets> iconsets) {
        this.iconsets = iconsets;
    }

    public ArrayList<Iconsets> getIconsets() {
        return iconsets;
    }

    public void setIconsets(ArrayList<Iconsets> iconsets) {
        this.iconsets = iconsets;
    }

    @Override
    public String toString() {
        return "IconsetObject{" +
                "iconsets=" + iconsets +
                '}';
    }
}
