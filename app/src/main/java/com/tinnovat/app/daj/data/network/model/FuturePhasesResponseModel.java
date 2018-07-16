package com.tinnovat.app.daj.data.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FuturePhasesResponseModel {
    @SerializedName("futurephases")
    @Expose
    private List<Futurephase> futurephases = null;

    public List<Futurephase> getFuturephases() {
        return futurephases;
    }

    public void setFuturephases(List<Futurephase> futurephases) {
        this.futurephases = futurephases;
    }

}
