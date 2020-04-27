package com.example.imitationjd.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;

import com.example.imitationjd.weiget.MyThiredBottomLinLerLayout;

public class MyViewModel extends ViewModel {
    private final MutableLiveData<RecyclerView> recyclerViewMutableLiveData = new MutableLiveData<>();
    private final MutableLiveData<MyThiredBottomLinLerLayout> myThiredLinLerLayoutMutableLiveData = new MutableLiveData<>();
    private final MutableLiveData<Integer> integerMutableLiveData = new MutableLiveData<>();

    public MutableLiveData<Integer> getIntegerMutableLiveData() {
        return integerMutableLiveData;
    }

    public MutableLiveData<RecyclerView> getSelected() {
        return recyclerViewMutableLiveData;
    }

    public MutableLiveData<MyThiredBottomLinLerLayout> getMyThiredLinLerLayoutMutableLiveData() {
        return myThiredLinLerLayoutMutableLiveData;
    }

}
