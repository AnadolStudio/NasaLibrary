package com.anadolstudio.nasalibrary.repository;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NMILink implements Parcelable {
    public static final Parcelable.Creator<NMILink> CREATOR = new Parcelable.Creator<NMILink>() {
        @Override
        public NMILink createFromParcel(Parcel in) {
            return new NMILink(in);
        }

        @Override
        public NMILink[] newArray(int size) {
            return new NMILink[size];
        }
    };
    @SerializedName("href")
    @Expose
    private String href;
    @SerializedName("rel")
    @Expose
    private String rel;
    @SerializedName("render")
    @Expose
    private String render;

    protected NMILink(Parcel in) {
        href = in.readString();
        rel = in.readString();
        render = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(href);
        dest.writeString(rel);
        dest.writeString(render);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getRel() {
        return rel;
    }

    public void setRel(String rel) {
        this.rel = rel;
    }

    public String getRender() {
        return render;
    }

    public void setRender(String render) {
        this.render = render;
    }


}
