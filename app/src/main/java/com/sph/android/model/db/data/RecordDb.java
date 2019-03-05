package com.sph.android.model.db.data;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class RecordDb extends RealmObject {

    @PrimaryKey
    private String volumeOfMobileData;
    private String year;
    private boolean showImage;

    public String getVolumeOfMobileData() {
        return volumeOfMobileData;
    }

    public void setVolumeOfMobileData(String volumeOfMobileData) {
        this.volumeOfMobileData = volumeOfMobileData;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public boolean isShowImage() {
        return showImage;
    }

    public void setShowImage(boolean showImage) {
        this.showImage = showImage;
    }
}