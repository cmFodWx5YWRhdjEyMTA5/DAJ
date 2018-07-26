package com.tinnovat.app.daj.data.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.tinnovat.app.daj.utils.CommonUtils;

import java.util.Calendar;
import java.util.List;

public class EventDetails {

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
    private boolean dateCategory;

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

    public String getDateCategory() {

        String category = "NULL";
        Calendar today = Calendar.getInstance();
        Calendar startDate = CommonUtils.getInstance().getDateFromServerResponse(getStartDatetime());
        Calendar endDate = CommonUtils.getInstance().getDateFromServerResponse(getEndDatetime());
        if (today.DATE == startDate.DATE || today.DATE == endDate.DATE) {
            category = "TODAY";
        } else {
            if (today.YEAR == startDate.YEAR && today.YEAR == endDate.YEAR) {
                if (today.MONTH == startDate.MONTH && today.MONTH == endDate.MONTH) {
                    if (startDate.DAY_OF_MONTH > today.DAY_OF_MONTH) {
                        if (startDate.DAY_OF_MONTH + 1 == today.DAY_OF_MONTH)
                            category = "TOMORROW";
                    }
                }
            } else if (today.YEAR == endDate.YEAR ) {
                if (today.MONTH == endDate.MONTH) {
                    if (today.DAY_OF_MONTH == endDate.DAY_OF_MONTH) {
                        category = "TODAY";
                    }else if (today.DAY_OF_MONTH == endDate.DAY_OF_MONTH+1) {
                        category = "TOMORROW";
                    }
                }
            } else if (today.YEAR == startDate.YEAR ) {
                if (today.MONTH == startDate.MONTH) {
                    if (today.DAY_OF_MONTH == startDate.DAY_OF_MONTH) {
                        category = "TODAY";
                    } else if (today.DAY_OF_MONTH == startDate.DAY_OF_MONTH+1) {
                        category = "TOMORROW";
                    }
                }
            }
        }
        return category;
    }
}
