package com.tinnovat.app.daj.data.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Notifications {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("categories_id")
    @Expose
    private Integer categoriesId;
    @SerializedName("guests_capacity")
    @Expose
    private Integer guestsCapacity;
    @SerializedName("start_datetime")
    @Expose
    private String startDatetime;
    @SerializedName("end_datetime")
    @Expose
    private String endDatetime;
    @SerializedName("events_name")
    @Expose
    private String eventsName;
    @SerializedName("events_description")
    @Expose
    private String eventsDescription;
    @SerializedName("events_venue")
    @Expose
    private String eventsVenue;
    @SerializedName("events_images")
    @Expose
    private List<EventsImage> eventsImages = null;
    @SerializedName("interest")
    @Expose
    private Boolean interest;
    @SerializedName("user_interested")
    @Expose
    private Boolean userInterested;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("DateTime")
    @Expose
    private String dateTime;
    @SerializedName("Purpose")
    @Expose
    private Integer purpose;
    @SerializedName("VehicleNo")
    @Expose
    private String vehicleNo;
    @SerializedName("notify_time")
    @Expose
    private String notifyTime;
    @SerializedName("register_no")
    @Expose
    private Integer registerNo;
    @SerializedName("categoryName")
    @Expose
    private String categoryName;
    @SerializedName("complaint_status")
    @Expose
    private Integer complaintStatus;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCategoriesId() {
        return categoriesId;
    }

    public void setCategoriesId(Integer categoriesId) {
        this.categoriesId = categoriesId;
    }

    public Integer getGuestsCapacity() {
        return guestsCapacity;
    }

    public void setGuestsCapacity(Integer guestsCapacity) {
        this.guestsCapacity = guestsCapacity;
    }

    public String getStartDatetime() {
        return startDatetime;
    }

    public void setStartDatetime(String startDatetime) {
        this.startDatetime = startDatetime;
    }

    public String getEndDatetime() {
        return endDatetime;
    }

    public void setEndDatetime(String endDatetime) {
        this.endDatetime = endDatetime;
    }

    public String getEventsName() {
        return eventsName;
    }

    public void setEventsName(String eventsName) {
        this.eventsName = eventsName;
    }

    public String getEventsDescription() {
        return eventsDescription;
    }

    public void setEventsDescription(String eventsDescription) {
        this.eventsDescription = eventsDescription;
    }

    public String getEventsVenue() {
        return eventsVenue;
    }

    public void setEventsVenue(String eventsVenue) {
        this.eventsVenue = eventsVenue;
    }

    public List<EventsImage> getEventsImages() {
        return eventsImages;
    }

    public void setEventsImages(List<EventsImage> eventsImages) {
        this.eventsImages = eventsImages;
    }

    public Boolean getInterest() {
        return interest;
    }

    public void setInterest(Boolean interest) {
        this.interest = interest;
    }

    public Boolean getUserInterested() {
        return userInterested;
    }

    public void setUserInterested(Boolean userInterested) {
        this.userInterested = userInterested;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public Integer getPurpose() {
        return purpose;
    }

    public void setPurpose(Integer purpose) {
        this.purpose = purpose;
    }

    public String getVehicleNo() {
        return vehicleNo;
    }

    public void setVehicleNo(String vehicleNo) {
        this.vehicleNo = vehicleNo;
    }

    public String getNotifyTime() {
        return notifyTime;
    }

    public void setNotifyTime(String notifyTime) {
        this.notifyTime = notifyTime;
    }

    public Integer getRegisterNo() {
        return registerNo;
    }

    public void setRegisterNo(Integer registerNo) {
        this.registerNo = registerNo;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Integer getComplaintStatus() {
        return complaintStatus;
    }

    public void setComplaintStatus(Integer complaintStatus) {
        this.complaintStatus = complaintStatus;
    }
}
