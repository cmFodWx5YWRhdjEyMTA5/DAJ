package com.tinnovat.app.daj.data.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CameraList {

    @SerializedName("camera_image")
    @Expose
    private String cameraImage;

    @SerializedName("camera_ip")
    @Expose
    private String cameraIp;

    @SerializedName("category")
    @Expose
    private String category;

    public String getCameraImage() {
        return cameraImage;
    }

    public void setCameraImage(String cameraImage) {
        this.cameraImage = cameraImage;
    }

    public String getCameraIp() {
        return cameraIp;
    }

    public void setCameraIp(String cameraIp) {
        this.cameraIp = cameraIp;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
