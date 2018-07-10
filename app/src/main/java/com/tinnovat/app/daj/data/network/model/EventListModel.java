package com.tinnovat.app.daj.data.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class EventListModel {

    @SerializedName("category")
    @Expose
    private List<EventCategory> category = null;

    public List<EventCategory> getCategory() {
        return category;
    }

    public void setCategory(List<EventCategory> category) {
        this.category = category;
    }
}
