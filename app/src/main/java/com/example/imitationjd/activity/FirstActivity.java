package com.example.imitationjd.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import com.example.imitationjd.R;
import com.example.imitationjd.adapter.FragmentAdapter;
import com.example.imitationjd.fragment.BottomFragment;
import com.example.imitationjd.viewmodel.MyViewModel;
import com.example.imitationjd.weiget.MyLinLerLayout;

import java.util.ArrayList;
import java.util.List;

public class FirstActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener{
    MyLinLerLayout myLinLerLayout;
    ViewPager viewPager;
    List<BottomFragment> fragmentList = new ArrayList<>();
    MyViewModel myViewModel;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        myLinLerLayout = this.findViewById(R.id.first_my_linlayout);
        myLinLerLayout.setFirstActivity(this);
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
