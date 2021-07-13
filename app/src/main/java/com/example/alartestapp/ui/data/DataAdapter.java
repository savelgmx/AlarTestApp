package com.example.alartestapp.ui.data;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.alartestapp.R;
import com.example.alartestapp.model.DataResponse;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.annotations.NonNull;

public class DataAdapter extends RecyclerView.Adapter<DataHolder> {
   @NonNull
   private final List<DataResponse> mData = new ArrayList<>();
   private final OnItemClickListener mOnItemClickListener;

   public DataAdapter(OnItemClickListener onItemClickListener) {
      mOnItemClickListener = onItemClickListener;
   }

   @NonNull
   @Override
   public DataHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      LayoutInflater inflater = LayoutInflater.from(parent.getContext());
      View view = inflater.inflate(R.layout.li_data,parent,false);
      return new DataHolder(view);
   }

   @Override
   public void onBindViewHolder(@NonNull DataHolder holder, int position) {
      DataResponse dataResponse = mData.get(position);
      holder.bind(dataResponse,mOnItemClickListener);

   }

   @Override
   public int getItemCount() {
      return mData.size();
   }


   public void addData(List<DataResponse> data, boolean isRefreshed) {
      if (isRefreshed) {
         mData.clear();
      }



      mData.addAll(data);
      notifyDataSetChanged();
   }


   public interface OnItemClickListener {

      void onItemClick(String lan,String lon);
   }

}

