package com.example.imitationjd.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewModel extends ViewModel {
    private final MutableLiveData<RecyclerView> recyclerViewMutableLiveData = new MutableLiveData<>();
    public void select(RecyclerView recyclerView){
        recyclerViewMutableLiveData.postValue(recyclerView);
    }

    public  MutableLiveData<RecyclerView> getSelected(){
        return recyclerViewMutableLiveData;
    }

}
