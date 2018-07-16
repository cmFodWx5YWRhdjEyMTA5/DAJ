package com.tinnovat.app.daj.data.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Futurephase {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("future_phase_id")
    @Expose
    private Integer futurePhaseId;
    @SerializedName("area")
    @Expose
    private String area;
    @SerializedName("locationlat")
    @Expose
    private String locationlat;
    @SerializedName("locationlng")
    @Expose
    private String locationlng;
    @SerializedName("location")
    @Expose
    private String location;
    @SerializedName("phase_name")
    @Expose
    private String phaseName;
    @SerializedName("desc")
    @Expose
    private String desc;
    @SerializedName("phase_images")
    @Expose
    private List<PhaseImage> phaseImages = null;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFuturePhaseId() {
        return futurePhaseId;
    }

    public void setFuturePhaseId(Integer futurePhaseId) {
        this.futurePhaseId = futurePhaseId;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getLocationlat() {
        return locationlat;
    }

    public void setLocationlat(String locationlat) {
        this.locationlat = locationlat;
    }

    public String getLocationlng() {
        return locationlng;
    }

    public void setLocationlng(String locationlng) {
        this.locationlng = locationlng;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPhaseName() {
        return phaseName;
    }

    public void setPhaseName(String phaseName) {
        this.phaseName = phaseName;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public List<PhaseImage> getPhaseImages() {
        return phaseImages;
    }

    public void setPhaseImages(List<PhaseImage> phaseImages) {
        this.phaseImages = phaseImages;
    }
}
