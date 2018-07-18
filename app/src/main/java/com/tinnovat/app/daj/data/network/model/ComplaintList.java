package com.tinnovat.app.daj.data.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ComplaintList {
    @SerializedName("register_no")
    @Expose
    private Integer registerNo;
    @SerializedName("categoryName")
    @Expose
    private String categoryName;
    @SerializedName("complaint_status")
    @Expose
    private Integer complaintStatus;
    @SerializedName("submitted_date")
    @Expose
    private String submittedDate;
    @SerializedName("location")
    @Expose
    private Locations location;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("log_details")
    @Expose
    private List<LogDetail> logDetails = null;
    @SerializedName("images")
    @Expose
    private List<Images> images = null;
    @SerializedName("user_id")
    @Expose
    private Integer userId;

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

    public String getSubmittedDate() {
        return submittedDate;
    }

    public void setSubmittedDate(String submittedDate) {
        this.submittedDate = submittedDate;
    }

    public Locations getLocation() {
        return location;
    }

    public void setLocation(Locations location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<LogDetail> getLogDetails() {
        return logDetails;
    }

    public void setLogDetails(List<LogDetail> logDetails) {
        this.logDetails = logDetails;
    }

    public List<Images> getImages() {
        return images;
    }

    public void setImages(List<Images> images) {
        this.images = images;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
