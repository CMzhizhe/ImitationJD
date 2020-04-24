package com.example.imitationjd.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.imitationjd.R;
import com.example.imitationjd.model.ShopModel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class HeaderAdapter extends RecyclerView.Adapter<HeaderAdapter.HeaderViewHolder>{
    List<String> shopModelArrayList;
    Activity activity;

    public HeaderAdapter(List<String> shopModelArrayList, Activity activity) {
        this.shopModelArrayList = shopModelArrayList;
        this.activity = activity;
    }

    @NonNull
    @Override
    public HeaderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = activity.getLayoutInflater().inflate(R.layout.item_header,parent,false);
        return new HeaderViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull HeaderViewHolder holder, int position) {
        if (position % 2 == 0){
            holder.textView.setBackgroundColor(activity.getResources().getColor(R.color.colorAccent));
        }else {
            holder.textView.setBackgroundColor(activity.getResources().getColor(R.color.colorPrimary));
        }
        holder.textView.setText(shopModelArrayList.get(position));
    }



    @Override
    public int getItemCount() {
        return shopModelArrayList.size();
    }

    static class HeaderViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        public HeaderViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.item_text);
        }
    }

}
