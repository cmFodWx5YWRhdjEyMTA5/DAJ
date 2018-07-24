package com.tinnovat.app.daj.data.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ServiceBooking {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("serviceCategory")
    @Expose
    private String serviceCategory;
    @SerializedName("service")
    @Expose
    private String service;
    @SerializedName("GustNo")
    @Expose
    private Integer gustNo;
    @SerializedName("bookingDate")
    @Expose
    private String bookingDate;
    @SerializedName("Comments")
    @Expose
    private String comments;
    @SerializedName("TimeSlots")
    @Expose
    private List<TimeSlot> timeSlots = null;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getServiceCategory() {
        return serviceCategory;
    }

    public void setServiceCategory(String serviceCategory) {
        this.serviceCategory = serviceCategory;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public Integer getGustNo() {
        return gustNo;
    }

    public void setGustNo(Integer gustNo) {
        this.gustNo = gustNo;
    }

    public String getServiceBookingDate() {
        return bookingDate;
    }

    public void setServiceBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public List<TimeSlot> getTimeSlots() {
        return timeSlots;
    }

    public void setTimeSlots(List<TimeSlot> timeSlots) {
        this.timeSlots = timeSlots;
    }

}
