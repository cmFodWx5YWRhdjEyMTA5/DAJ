package com.tinnovat.app.daj.data.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ServicesResponseModel {
    @SerializedName("category")
    @Expose
    private List<ServiceCategory> category = null;

    public List<ServiceCategory> getServiceCategory() {
        return category;
    }

    public void setServiceCategory(List<ServiceCategory> serviceCategory) {
        this.category = serviceCategory;
    }
}
