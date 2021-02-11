package com.anadolstudio.nasalibrary.repository;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ItemCollection {

    @SerializedName("href")
    @Expose
    private String href;
    @SerializedName("items")
    @Expose
    private List<NasaMediaItem> items = null;
    @SerializedName("links")
    @Expose
    private List<CollectionLink> links = null;
    @SerializedName("metadata")
    @Expose
    private Metadata metadata;
    @SerializedName("version")
    @Expose
    private String version;

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public List<NasaMediaItem> getItems() {
        return items;
    }

    public void setItems(List<NasaMediaItem> items) {
        this.items = items;
    }

    public List<CollectionLink> getLinks() {
        return links;
    }

    public void setLinks(List<CollectionLink> links) {
        this.links = links;
    }

    public Metadata getMetadata() {
        return metadata;
    }

    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }


}
