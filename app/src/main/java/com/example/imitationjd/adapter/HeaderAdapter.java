package com.example.imitationjd.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.imitationjd.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class HeaderAdapter extends RecyclerView.Adapter<HeaderAdapter.ViewHolder>{
    List<String> headerModelList;
    Activity activity;

    public HeaderAdapter(List<String> headerModelList, Activity activity) {
        this.headerModelList = headerModelList;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = activity.getLayoutInflater().inflate(R.layout.item_header,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (position % 2 == 0){
            holder.textView.setBackgroundColor(activity.getResources().getColor(R.color.colorAccent));
        }else {
            holder.textView.setBackgroundColor(activity.getResources().getColor(R.color.colorPrimary));
        }
        holder.textView.setText(headerModelList.get(position));
    }

    @Override
    public int getItemCount() {
        return headerModelList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.item_text);
        }
    }
}
