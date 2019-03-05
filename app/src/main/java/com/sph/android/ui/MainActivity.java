package com.sph.android.ui;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.sph.android.R;
import com.sph.android.adapter.RecordAdapter;
import com.sph.android.model.db.RealmManager;
import com.sph.android.model.db.data.RecordDb;
import com.sph.android.model.rest.DataResponse;
import com.sph.android.model.rest.Record;
import com.sph.android.service.ApiClient;
import com.sph.android.service.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private final static String API_KEY = "a807b7ab-6cad-4aa6-87d0-e283a7353a0f";
    private RecyclerView recyclerView;

    public static List<RecordDb> getAdapterRecords(List<Record> records) {
        String prevYear = records.get(0).getQuarter().trim().substring(0, 4);
        double prevDataVolume = Double.parseDouble(records.get(0).getVolumeOfMobileData());
        double totalDataVol = prevDataVolume;
        boolean showImage = false;
        List<RecordDb> newRecords = new ArrayList<>();

        for (int i = 1; i < records.size(); i++) {
            if (prevYear.equalsIgnoreCase(records.get(i).getQuarter().trim().substring(0, 4))) {
                if (prevDataVolume > Double.parseDouble(records.get(i).getVolumeOfMobileData())) {
                    showImage = true;
                }
                totalDataVol += Double.parseDouble(records.get(i).getVolumeOfMobileData());
                prevDataVolume = Double.parseDouble(records.get(i).getVolumeOfMobileData());
            } else {
                RecordDb rec = new RecordDb();
                rec.setYear(prevYear);
                rec.setVolumeOfMobileData(Double.toString(totalDataVol));
                rec.setShowImage(showImage);
                newRecords.add(rec);

                showImage = false;
                prevYear = records.get(i).getQuarter().trim().substring(0, 4);
                prevDataVolume = Double.parseDouble(records.get(i).getVolumeOfMobileData());
                totalDataVol = prevDataVolume;
                //check last record is new year and has only one quarter
                if (i + 1 == records.size()) {
                    RecordDb rec2 = new RecordDb();
                    rec2.setYear(prevYear);
                    rec2.setVolumeOfMobileData(Double.toString(totalDataVol));
                    rec2.setShowImage(showImage);
                    newRecords.add(rec2);
                }
            }
        }
        //only single year records presents
        if (records.size() > 0 && newRecords.size() == 0) {
            RecordDb rec = new RecordDb();
            rec.setYear(prevYear);
            rec.setVolumeOfMobileData(Double.toString(totalDataVol));
            rec.setShowImage(showImage);
            newRecords.add(rec);
        }
        return newRecords;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Volume Of Mobile Data");

        setContentView(R.layout.activity_main);
        RealmManager.open();

        recyclerView = findViewById(R.id.rvMobileData);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        if (!isNetworkConnected(this)) {
            loadList();
            Toast.makeText(this, "No Internet connection. data load from the db", Toast.LENGTH_LONG).show();
        } else {
            getDataFromServer();
        }

    }

    private void getDataFromServer() {
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<DataResponse> call = apiService.getRecords(API_KEY, 500);
        call.enqueue(new Callback<DataResponse>() {
            @Override
            public void onResponse(Call<DataResponse> call, Response<DataResponse> response) {
                int statusCode = response.code();
                if (statusCode == 200) {
                    List<Record> records = response.body().getResult().getRecords();
                    List<RecordDb> newRecords = getAdapterRecords(records);
                    saveAdapterRecords(newRecords);
                    loadList();
                } else {
                    Toast.makeText(MainActivity.this, "Server connection failed.", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<DataResponse> call, Throwable t) {
                // Log error here since request failed
                Toast.makeText(MainActivity.this, "Server connection failed." + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void saveAdapterRecords(List<RecordDb> newRecords) {
        RealmManager.getRecordDao().save(newRecords);
    }

    private void loadList() {
        final RealmResults<RecordDb> records = RealmManager.getRecordDao().loadAllRecords();
        recyclerView.setAdapter(new RecordAdapter(records, R.layout.list_item_record, getApplicationContext()));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RealmManager.close();
    }

    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;
    }

}
