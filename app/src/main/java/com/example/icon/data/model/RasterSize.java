package com.example.icon.data.model;

import com.example.icon.data.model.Formats;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class RasterSize {
    @SerializedName("formats")
    @Expose
    private ArrayList<Formats> formats;

    public RasterSize(ArrayList<Formats> formats, int size) {
        this.formats = formats;
        this.size = size;
    }

    @SerializedName("size")
    @Expose
    private int size;

    @Override
    public String toString() {
        return "RasterSize{" +
                "formats=" + formats +
                ", size=" + size +
                '}';
    }

    public ArrayList<Formats> getFormats() {
        return formats;
    }

    public void setFormats(ArrayList<Formats> formats) {
        this.formats = formats;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
