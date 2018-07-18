package com.tinnovat.app.daj.data.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Locations {
    @SerializedName("lat:")
    @Expose
    private Float lat;
    @SerializedName("long:")
    @Expose
    private Float _long;

    public Float getLat() {
        return lat;
    }

    public void setLat(Float lat) {
        this.lat = lat;
    }

    public Float getLong() {
        return _long;
    }

    public void setLong(Float _long) {
        this._long = _long;
    }
}
