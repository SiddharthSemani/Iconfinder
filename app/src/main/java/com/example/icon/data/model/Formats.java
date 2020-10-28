package com.example.icon.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Formats {
    @SerializedName("download_url")
    @Expose
    private String download_url;

    @SerializedName("preview_url")
    @Expose
    private String preview_url;

    @Override
    public String toString() {
        return "Formats{" +
                "download_url='" + download_url + '\'' +
                ", preview_url='" + preview_url + '\'' +
                '}';
    }

    public String getDownload_url() {
        return download_url;
    }

    public void setDownload_url(String download_url) {
        this.download_url = download_url;
    }

    public String getPreview_url() {
        return preview_url;
    }

    public void setPreview_url(String preview_url) {
        this.preview_url = preview_url;
    }

    public Formats(String download_url, String preview_url) {
        this.download_url = download_url;
        this.preview_url = preview_url;
    }
}
