package com.example.imitationjd.activity;

import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.imitationjd.R;
import com.example.imitationjd.adapter.ThiredAdapter;
import com.example.imitationjd.model.ShopModel;
import com.example.imitationjd.weiget.MyFrameLinlerLayout;
import com.example.imitationjd.weiget.NestedLinLerLayout;

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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thired);
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

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        thiredAdapter = new ThiredAdapter(shopModelList,this);
        recyclerView.setAdapter(thiredAdapter);
    }
}
