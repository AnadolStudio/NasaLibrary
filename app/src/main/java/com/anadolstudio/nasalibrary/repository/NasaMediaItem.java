package com.anadolstudio.nasalibrary.repository;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NasaMediaItem implements Parcelable {
    public static final String NASA_MEDIA_ITEM = "nasa_media_item";
    public static final Creator<NasaMediaItem> CREATOR = new Creator<NasaMediaItem>() {
        @Override
        public NasaMediaItem createFromParcel(Parcel in) {
            return new NasaMediaItem(in);
        }

        @Override
        public NasaMediaItem[] newArray(int size) {
            return new NasaMediaItem[size];
        }
    };
    @SerializedName("data")
    @Expose
    private List<Datum> data = null;
    @SerializedName("href")
    @Expose
    private String href;
    @SerializedName("links")
    @Expose
    private List<NMILink> links = null;

    protected NasaMediaItem(Parcel in) {
        data = in.createTypedArrayList(Datum.CREATOR);
        href = in.readString();
        links = in.createTypedArrayList(NMILink.CREATOR);
    }

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public List<NMILink> getLinks() {
        return links;
    }

    public void setLinks(List<NMILink> links) {
        this.links = links;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(data);
        dest.writeString(href);
        dest.writeTypedList(links);
    }


}
