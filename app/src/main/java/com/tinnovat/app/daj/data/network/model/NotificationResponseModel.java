package com.tinnovat.app.daj.data.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NotificationResponseModel {
    @SerializedName("notification")
    @Expose
    private List<Notifications> notification = null;

    public List<Notifications> getNotification() {
        return notification;
    }

    public void setNotification(List<Notifications> notification) {
        this.notification = notification;
    }
}
