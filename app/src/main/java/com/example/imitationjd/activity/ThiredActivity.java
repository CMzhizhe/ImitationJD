package com.example.imitationjd.activity;

import android.os.Bundle;
import android.view.ViewTreeObserver;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.imitationjd.R;
import com.example.imitationjd.adapter.ThiredAdapter;
import com.example.imitationjd.model.ShopModel;
import com.example.imitationjd.viewmodel.MyViewModel;
import com.example.imitationjd.weiget.NestedFrameLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * @date 创建时间:2020/4/26 0026
 * @auther gaoxiaoxiong
 * @Descriptiion 参考网址  https://www.jianshu.com/p/20efb9f65494
 **/
public class ThiredActivity extends AppCompatActivity {
    public static final int THIRED_TYPE_0=0;
    public static final int THIRED_TYPE_1=1;
    private RecyclerView recyclerView;
    private ThiredAdapter thiredAdapter;
    private List<ShopModel> shopModelList = new ArrayList<>();
    private NestedFrameLayout nestedFrameLayout;
    private MyViewModel myViewModel;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thired);
        nestedFrameLayout = this.findViewById(R.id.thired_framelayout);
        recyclerView = this.findViewById(R.id.thired_recyclerview);
        for (int i = 0; i < 30; i++) {
            ShopModel shopModel = new ShopModel();
            shopModel.setTextString("我是头部:"+i);
            shopModel.setType(THIRED_TYPE_0);
            shopModelList.add(shopModel);
        }
        ShopModel shopModel = new ShopModel();
        shopModel.setType(THIRED_TYPE_1);
        shopModelList.add(shopModel);
        nestedFrameLayout.setThiredActivity(this,recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        thiredAdapter = new ThiredAdapter(shopModelList,this);
        recyclerView.setAdapter(thiredAdapter);
        myViewModel = new ViewModelProvider(this.getViewModelStore(), ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(MyViewModel.class);
        nestedFrameLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
               int height =  nestedFrameLayout.getHeight();
               myViewModel.getIntegerMutableLiveData().setValue(height);
            }
        });

        recyclerView.setNestedScrollingEnabled(true);
    }
}
