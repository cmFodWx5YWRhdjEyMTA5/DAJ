package com.tinnovat.app.daj.data.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ServiceAvailableDate {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("slots_name")
    @Expose
    private String slotsName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSlotsName() {
        return slotsName;
    }

    public void setSlotsName(String slotsName) {
        this.slotsName = slotsName;
    }

}
