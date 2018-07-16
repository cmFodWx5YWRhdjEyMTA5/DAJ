package com.tinnovat.app.daj.data.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Service {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("service_images")
    @Expose
    private String serviceImages;
    @SerializedName("availableDates")
    @Expose
    private List<ServiceAvailableDate> serviceAvailableDates = null;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getServiceImages() {
        return serviceImages;
    }

    public void setServiceImages(String serviceImages) {
        this.serviceImages = serviceImages;
    }

    public List<ServiceAvailableDate> getServiceAvailableDates() {
        return serviceAvailableDates;
    }

    public void setServiceAvailableDates(List<ServiceAvailableDate> serviceAvailableDates) {
        this.serviceAvailableDates = serviceAvailableDates;
    }

}
