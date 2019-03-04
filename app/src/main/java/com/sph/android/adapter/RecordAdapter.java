package com.sph.android.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sph.android.R;
import com.sph.android.model.rest.Record;

import java.util.List;

public class RecordAdapter extends RecyclerView.Adapter<RecordAdapter.RecordViewHolder> {

    private List<Record> records;
    private int rowLayout;
    private Context context;


    public RecordAdapter(List<Record> records, int rowLayout, Context context) {
        this.records = records;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @Override
    public RecordAdapter.RecordViewHolder onCreateViewHolder(ViewGroup parent,
                                                             int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new RecordViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecordViewHolder holder, final int position) {
        holder.tvQuarter.setText(records.get(position).getQuarter());
        holder.tvVolume.setText(records.get(position).getVolumeOfMobileData());
    }

    @Override
    public int getItemCount() {
        return records.size();
    }

    public static class RecordViewHolder extends RecyclerView.ViewHolder {
        TextView tvQuarter;
        TextView tvVolume;
        ImageView ivLowVolume;


        public RecordViewHolder(View v) {
            super(v);
            tvQuarter = v.findViewById(R.id.tvQuarter);
            tvVolume = v.findViewById(R.id.tvVolume);
            ivLowVolume = v.findViewById(R.id.ivLowVolume);
        }
    }
}