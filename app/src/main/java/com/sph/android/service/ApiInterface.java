package com.sph.android.service;

import com.sph.android.model.rest.DataResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface ApiInterface {

    @GET("datastore_search")
    Call<DataResponse> getRecords(@Query("resource_id") String apiKey, @Query("limit") int limit);

}
