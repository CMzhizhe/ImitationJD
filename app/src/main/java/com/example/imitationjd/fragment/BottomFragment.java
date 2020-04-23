package com.example.imitationjd.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.imitationjd.R;
import com.example.imitationjd.activity.FirstActivity;
import com.example.imitationjd.adapter.BottomAdapter;

import java.util.ArrayList;
import java.util.List;

public class BottomFragment extends Fragment {
    RecyclerView recyclerView;
    BottomAdapter bottomAdapter;
    private int currentIndex = 0;
    private boolean isFirstLoad = true;

    private List<String> stringList = new ArrayList<>();
    public BottomFragment(int currentIndex) {
        super();
        this.currentIndex = currentIndex;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_layout, container, false);
        init(view);
        return view;
    }

    private void init(View view) {
        recyclerView = view.findViewById(R.id.fragment_recyclerview);
        for (int i = 0; i < 50; i++) {
            stringList.add("哈哈哈哈-" + currentIndex +"-"+ i);
        }
        bottomAdapter = new BottomAdapter(this.getActivity(), stringList);
        LinearLayoutManager linearLayout = new LinearLayoutManager(this.getContext());
        recyclerView.setLayoutManager(linearLayout);
        recyclerView.setAdapter(bottomAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (getActivity()!=null && currentIndex == 0 && isFirstLoad){
            isFirstLoad = false;
            FirstActivity activity = (FirstActivity) getActivity();
            activity.onPageSelected(currentIndex);
        }
    }

    public RecyclerView getRecyclerView() {
        return recyclerView;
    }
}
