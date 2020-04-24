package com.example.imitationjd.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.imitationjd.R;
import com.example.imitationjd.adapter.FragmentAdapter;
import com.example.imitationjd.adapter.HeaderAdapter;
import com.example.imitationjd.fragment.BottomFragment;
import com.example.imitationjd.viewmodel.MyViewModel;
import com.example.imitationjd.weiget.MyFirstLinLerLayout;
import com.example.imitationjd.weiget.MyMainLinLerLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener{
    //头部
    private RecyclerView recyclerHeaderView;
    private HeaderAdapter headerAdapter;
    private List<String> headerStringArray = new ArrayList<>();

    //底部
    MyMainLinLerLayout myFirstLinLerLayout;
    ViewPager viewPager;
    List<BottomFragment> fragmentList = new ArrayList<>();
    MyViewModel myViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //头部
        recyclerHeaderView = this.findViewById(R.id.main_header_recyclerview);
        for (int i=0;i<30;i++){
            headerStringArray.add("我是标题:"+i);
        }
        LinearLayoutManager headerLinLayoutManager = new LinearLayoutManager(this);
        headerAdapter = new HeaderAdapter(headerStringArray,this);
        recyclerHeaderView.setLayoutManager(headerLinLayoutManager);
        recyclerHeaderView.setAdapter(headerAdapter);

        //底部
        myFirstLinLerLayout = this.findViewById(R.id.first_my_linlayout);
        myFirstLinLerLayout.setFirstActivity(this);
        viewPager = this.findViewById(R.id.first_viewpager);
        for (int i = 0; i < 4; i++) {
            fragmentList.add(new BottomFragment(i));
        }
        viewPager.setAdapter(new FragmentAdapter(this.getSupportFragmentManager(),fragmentList));
        viewPager.addOnPageChangeListener(this);
        viewPager.setOffscreenPageLimit(4);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        initViewModel();
        if (myViewModel!=null){
            myViewModel.getSelected().setValue(fragmentList.get(position).getRecyclerView());
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private void initViewModel(){
        if (myViewModel == null ){
            myViewModel =  new ViewModelProvider(this.getViewModelStore(), ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(MyViewModel.class);
        }
    }

}
