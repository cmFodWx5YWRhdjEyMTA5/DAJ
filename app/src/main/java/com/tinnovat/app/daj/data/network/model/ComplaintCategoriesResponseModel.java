package com.tinnovat.app.daj.data.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ComplaintCategoriesResponseModel {
    @SerializedName("categories")
    @Expose
    private List<ComplaintCategory> categories = null;

    public List<ComplaintCategory> getCategories() {
        return categories;
    }

    public void setCategories(List<ComplaintCategory> categories) {
        this.categories = categories;
    }
}
