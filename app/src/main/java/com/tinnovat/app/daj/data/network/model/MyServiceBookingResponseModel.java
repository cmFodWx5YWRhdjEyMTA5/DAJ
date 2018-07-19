package com.tinnovat.app.daj.data.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MyServiceBookingResponseModel {
    @SerializedName("Booking")
    @Expose
    private List<ServiceBooking> booking = null;

    public List<ServiceBooking> getServiceBooking() {
        return booking;
    }

    public void setServiceBooking(List<ServiceBooking> booking) {
        this.booking = booking;
    }
}
