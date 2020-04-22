package com.example.imitationjd;

import android.os.Bundle;
import android.widget.TableLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.imitationjd.adapter.HeaderAdapter;
import com.example.imitationjd.model.ContentModel;
import com.example.imitationjd.model.HeaderModel;
import com.example.imitationjd.model.BottomModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private HeaderAdapter headerAdapter;

    private List<HeaderModel> headerModelList = new ArrayList<>();
    private List<BottomModel> bottomModels = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = this.findViewById(R.id.main_header_recyclerview);
        for (int i = 0; i < 20; i++) {
            HeaderModel headerModel = new HeaderModel();
            headerModel.setName("我是header部分:" + i);
            headerModelList.add(headerModel);
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        headerAdapter = new HeaderAdapter(headerModelList, this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(headerAdapter);
        initBottom();
    }


    private void initBottom() {
        BottomModel bottomModel = new BottomModel();
        bottomModel.setName("精选");
        bottomModel.setDesc("为你推荐");
        bottomModel.setType(2);
        bottomModel.setSelected(true);
        List<ContentModel> contentModelList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            ContentModel contentModel = new ContentModel();
            contentModel.setName("我是内容1");
            contentModelList.add(contentModel);
        }
        bottomModel.setContentModelList(contentModelList);
        bottomModels.add(bottomModel);

        BottomModel bottomModel2 = new BottomModel();
        bottomModel2.setName("新品");
        bottomModel2.setDesc("春日唤新");
        bottomModel2.setType(2);
        List<ContentModel> contentModelList2 = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            ContentModel contentModel = new ContentModel();
            contentModel.setName("我是内容2");
            contentModelList2.add(contentModel);
        }
        bottomModel2.setContentModelList(contentModelList2);
        bottomModels.add(bottomModel2);


        BottomModel bottomModel3 = new BottomModel();
        bottomModel3.setName("直播");
        bottomModel3.setDesc("主播力推");
        bottomModel3.setType(2);
        List<ContentModel> contentModelList3 = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            ContentModel contentModel = new ContentModel();
            contentModel.setName("我是内容3");
            contentModelList3.add(contentModel);
        }
        bottomModel3.setContentModelList(contentModelList3);
        bottomModels.add(bottomModel3);

        BottomModel bottomModel4 = new BottomModel();
        bottomModel4.setName("实惠");
        bottomModel4.setDesc("超值好货");
        bottomModel4.setType(2);
        List<ContentModel> contentModelList4 = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            ContentModel contentModel = new ContentModel();
            contentModel.setName("我是内容4");
            contentModelList4.add(contentModel);
        }
        bottomModel4.setContentModelList(contentModelList4);
        bottomModels.add(bottomModel4);
    }
}
