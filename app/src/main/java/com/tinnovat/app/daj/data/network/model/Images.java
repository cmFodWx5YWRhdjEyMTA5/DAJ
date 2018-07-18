package com.tinnovat.app.daj.data.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Images {

    @SerializedName("path")
    @Expose
    private String path = null;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }


}
