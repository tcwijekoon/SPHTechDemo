package com.sph.android.model.rest;

import com.google.gson.annotations.SerializedName;

public class Record {
    @SerializedName("volume_of_mobile_data")
    private String volumeOfMobileData;
    @SerializedName("quarter")
    private String quarter;
    @SerializedName("_id")
    private String id;

    public String getVolumeOfMobileData() {
        return volumeOfMobileData;
    }

    public void setVolumeOfMobileData(String volumeOfMobileData) {
        this.volumeOfMobileData = volumeOfMobileData;
    }

    public String getQuarter() {
        return quarter;
    }

    public void setQuarter(String quarter) {
        this.quarter = quarter;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
