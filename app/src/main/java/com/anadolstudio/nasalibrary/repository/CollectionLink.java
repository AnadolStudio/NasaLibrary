package com.anadolstudio.nasalibrary.repository;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CollectionLink {

    @SerializedName("href")
    @Expose
    private String href;
    @SerializedName("prompt")
    @Expose
    private String prompt;
    @SerializedName("rel")
    @Expose
    private String rel;

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public String getRel() {
        return rel;
    }

    public void setRel(String rel) {
        this.rel = rel;
    }
}
