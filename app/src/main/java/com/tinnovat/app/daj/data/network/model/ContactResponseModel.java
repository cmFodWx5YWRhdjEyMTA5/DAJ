package com.tinnovat.app.daj.data.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ContactResponseModel {
    @SerializedName("Contact")
    @Expose
    private List<EmergencyContact> contact = null;

    public List<EmergencyContact> getContact() {
        return contact;
    }

    public void setContact(List<EmergencyContact> contact) {
        this.contact = contact;
    }
}
