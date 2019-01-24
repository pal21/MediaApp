package com.example.mediaapp.activity.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class News {

    @SerializedName("mTitle")
    @Expose
    private String mTitle;
    @SerializedName("rows")
    @Expose
    private List<Row> rows = null;

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public List<Row> getRows() {
        return rows;
    }

}