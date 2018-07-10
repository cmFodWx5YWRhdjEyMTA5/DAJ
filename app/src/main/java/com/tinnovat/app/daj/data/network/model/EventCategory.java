package com.tinnovat.app.daj.data.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class EventCategory {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("categoryName")
    @Expose
    private String categoryName;
    @SerializedName("icon")
    @Expose
    private String icon;
    @SerializedName("events")
    @Expose
    private List<EventDetails> events = null;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public List<EventDetails> getEvents() {
        return events;
    }

    public void setEvents(List<EventDetails> events) {
        this.events = events;
    }
}
