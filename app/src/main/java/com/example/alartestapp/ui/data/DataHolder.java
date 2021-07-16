package com.example.alartestapp.ui.data;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;


import com.example.alartestapp.R;
import com.example.alartestapp.model.Data;


public class DataHolder extends RecyclerView.ViewHolder {

    private TextView mName;
    private TextView mCountry;
    private TextView mLat;
    private TextView mLon;

    public DataHolder(View itemView) {
        super(itemView);
        mName = itemView.findViewById(R.id.tv_name);
        mCountry=itemView.findViewById(R.id.tv_country);
        mLat=itemView.findViewById(R.id.tv_lat);
        mLon=itemView.findViewById(R.id.tv_lon);


    }

    public void bind(Data.Datum item, DataAdapter.OnItemClickListener onItemClickListener) {


      //  mName.setText(item.getName());
        mCountry.setText("westeros");
        mLon.setText("lon");
        mLat.setText("hh");


        if (onItemClickListener != null) {
            itemView.setOnClickListener(v -> onItemClickListener.onItemClick(String.valueOf(mLon),String.valueOf(mLat)));
        }


    }
}
