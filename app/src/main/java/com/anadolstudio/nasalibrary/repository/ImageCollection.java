package com.anadolstudio.nasalibrary.repository;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ImageCollection {
    @SerializedName("href")
    @Expose
    private String href;
    @SerializedName("items")
    @Expose
    private List<ImageMedia> items = null;
    @SerializedName("version")
    @Expose
    private String version;

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public List<ImageMedia> getItems() {
        return items;
    }

    public void setItems(List<ImageMedia> items) {
        this.items = items;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
