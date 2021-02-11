package com.anadolstudio.nasalibrary.repository;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ImageMedia {
    public static final String ORIG = "orig";
    public static final String LARGE = "large";
    public static final String MEDIUM = "medium";
    public static final String SMALL = "small";
    public static final String THUMB = "thumb";

    @SerializedName("href")
    @Expose
    private String href;

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }
}
