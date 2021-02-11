package com.anadolstudio.nasalibrary.repository;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NasaItemResponse {

    @SerializedName("collection")
    @Expose
    private ItemCollection collection;

    public ItemCollection getCollection() {
        return collection;
    }

    public void setCollection(ItemCollection collection) {
        this.collection = collection;
    }
}
