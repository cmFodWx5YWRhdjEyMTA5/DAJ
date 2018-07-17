package com.tinnovat.app.daj.data.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LogDetail {
    @SerializedName("complaints_log_note")
    @Expose
    private Object complaintsLogNote;
    @SerializedName("complaint_status")
    @Expose
    private Integer complaintStatus;
    @SerializedName("submitted_date")
    @Expose
    private String submittedDate;
    @SerializedName("complaints_id")
    @Expose
    private Integer complaintsId;

    public Object getComplaintsLogNote() {
        return complaintsLogNote;
    }

    public void setComplaintsLogNote(Object complaintsLogNote) {
        this.complaintsLogNote = complaintsLogNote;
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

    public Integer getComplaintsId() {
        return complaintsId;
    }

    public void setComplaintsId(Integer complaintsId) {
        this.complaintsId = complaintsId;
    }
}
