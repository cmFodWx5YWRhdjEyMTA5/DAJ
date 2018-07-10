package com.tinnovat.app.daj.data.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

class EventDetails {

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
}
