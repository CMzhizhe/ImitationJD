package com.example.imitationjd.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.imitationjd.R;

import java.util.List;

public class BottomAdapter extends RecyclerView.Adapter<BottomAdapter.ViewHolder>{
    private Activity mActivity;
    private List<String> stringList;

    public BottomAdapter(Activity mActivity, List<String> stringList) {
        this.mActivity = mActivity;
        this.stringList = stringList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mActivity.getLayoutInflater().inflate(R.layout.item_bottom_text,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView.setText(stringList.get(position));
    }

    @Override
    public int getItemCount() {
        return stringList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tv_item_bottom_text);
        }
    }
}
