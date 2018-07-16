package com.tinnovat.app.daj.data.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ServiceSlots {

    @SerializedName("category")
    @Expose
    private Integer category;

    @SerializedName("service_type")
    @Expose
    private String service_type;

    @SerializedName("date")
    @Expose
    private String date;

    @SerializedName("availableSlots")
    @Expose
    private List<ServiceAvailableDate> serviceAvailableDates = null;

    public String getService_type() {
        return service_type;
    }

    public void setService_type(String service_type) {
        this.service_type = service_type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<ServiceAvailableDate> getServiceAvailableDates() {
        return serviceAvailableDates;
    }

    public void setServiceAvailableDates(List<ServiceAvailableDate> serviceAvailableDates) {
        this.serviceAvailableDates = serviceAvailableDates;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

}
