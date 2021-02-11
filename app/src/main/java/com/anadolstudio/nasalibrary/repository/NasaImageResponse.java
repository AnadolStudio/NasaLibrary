package com.anadolstudio.nasalibrary.repository;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NasaImageResponse {

    @SerializedName("collection")
    @Expose
    private ImageCollection collection;

    public ImageCollection getCollection() {
        return collection;
    }

    public void setCollection(ImageCollection collection) {
        this.collection = collection;
    }
}
