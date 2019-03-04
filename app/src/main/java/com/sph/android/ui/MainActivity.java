package com.sph.android.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.sph.android.R;
import com.sph.android.adapter.RecordAdapter;
import com.sph.android.model.rest.DataResponse;
import com.sph.android.model.rest.Record;
import com.sph.android.service.ApiClient;
import com.sph.android.service.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private final static String API_KEY = "a807b7ab-6cad-4aa6-87d0-e283a7353a0f";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final RecyclerView recyclerView =  findViewById(R.id.rvMobileData);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<DataResponse> call = apiService.getRecords(API_KEY, 50);
        call.enqueue(new Callback<DataResponse>() {
            @Override
            public void onResponse(Call<DataResponse> call, Response<DataResponse> response) {
                int statusCode = response.code();
                List<Record> movies = response.body().getResult().getRecords();
                recyclerView.setAdapter(new RecordAdapter(movies, R.layout.list_item_record, getApplicationContext()));
            }

            @Override
            public void onFailure(Call<DataResponse> call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
            }
        });
    }
}
