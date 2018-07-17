package com.tinnovat.app.daj.data.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ComplaintListResponseModel {
    @SerializedName("complaints")
    @Expose
    private List<ComplaintList> complaints = null;

    public List<ComplaintList> getComplaints() {
        return complaints;
    }

    public void setComplaints(List<ComplaintList> complaints) {
        this.complaints = complaints;
    }
}
