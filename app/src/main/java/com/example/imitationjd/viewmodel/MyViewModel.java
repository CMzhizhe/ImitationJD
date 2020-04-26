package com.example.imitationjd.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;

import com.example.imitationjd.weiget.MyThiredLinLerLayout;

public class MyViewModel extends ViewModel {
    private final MutableLiveData<RecyclerView> recyclerViewMutableLiveData = new MutableLiveData<>();
    private final MutableLiveData<MyThiredLinLerLayout> myThiredLinLerLayoutMutableLiveData = new MutableLiveData<>();

    public MutableLiveData<RecyclerView> getSelected() {
        return recyclerViewMutableLiveData;
    }

    public MutableLiveData<MyThiredLinLerLayout> getMyThiredLinLerLayoutMutableLiveData() {
        return myThiredLinLerLayoutMutableLiveData;
    }

}
