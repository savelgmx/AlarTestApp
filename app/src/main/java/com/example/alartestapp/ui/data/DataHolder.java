package com.example.alartestapp.ui.data;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;

import com.example.alartestapp.model.DataResponse;

public class DataHolder extends RecyclerView.ViewHolder {
    public DataHolder(View itemView) {
        super(itemView);

    }

    public void bind(DataResponse dataResponse, AdapterView.OnItemClickListener mOnItemClickListener) {
    }
}
