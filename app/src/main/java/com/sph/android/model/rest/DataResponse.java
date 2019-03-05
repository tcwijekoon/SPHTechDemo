package com.sph.android.model.rest;

import com.google.gson.annotations.SerializedName;

public class DataResponse {
    @SerializedName("success")
    private boolean success;
    @SerializedName("result")
    private Result result;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }
}
