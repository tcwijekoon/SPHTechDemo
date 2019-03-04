package com.sph.android.adapter.models;

public class AdapterRecord {
    private String volumeOfMobileData;
    private String quarter;
    private boolean showImage;

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

    public boolean isShowImage() {
        return showImage;
    }

    public void setShowImage(boolean showImage) {
        this.showImage = showImage;
    }
}
