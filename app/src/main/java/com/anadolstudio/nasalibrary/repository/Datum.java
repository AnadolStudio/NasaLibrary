package com.anadolstudio.nasalibrary.repository;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Datum implements Parcelable {

    public static final Creator<Datum> CREATOR = new Creator<Datum>() {
        @Override
        public Datum createFromParcel(Parcel in) {
            return new Datum(in);
        }

        @Override
        public Datum[] newArray(int size) {
            return new Datum[size];
        }
    };
    @SerializedName("center")
    @Expose
    private String center;
    @SerializedName("date_created")
    @Expose
    private String dateCreated;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("keywords")
    @Expose
    private List<String> keywords = null;
    @SerializedName("media_type")
    @Expose
    private String mediaType;
    @SerializedName("nasa_id")
    @Expose
    private String nasaId;
    @SerializedName("title")
    @Expose
    private String title;

    protected Datum(Parcel in) {
        center = in.readString();
        dateCreated = in.readString();
        description = in.readString();
        keywords = in.createStringArrayList();
        mediaType = in.readString();
        nasaId = in.readString();
        title = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(center);
        dest.writeString(dateCreated);
        dest.writeString(description);
        dest.writeStringList(keywords);
        dest.writeString(mediaType);
        dest.writeString(nasaId);
        dest.writeString(title);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public String getCenter() {
        return center;
    }

    public void setCenter(String center) {
        this.center = center;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<String> keywords) {
        this.keywords = keywords;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public String getNasaId() {
        return nasaId;
    }

    public void setNasaId(String nasaId) {
        this.nasaId = nasaId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}



