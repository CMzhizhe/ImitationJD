package com.example.imitationjd.weiget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.NestedScrollingParent3;
import androidx.core.view.ViewCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.imitationjd.activity.ThiredActivity;
import com.example.imitationjd.viewmodel.MyViewModel;

/**
 * @date 创建时间:2020/4/26 0026
 * @auther gaoxiaoxiong
 * @Descriptiion 嵌套滚动
 **/
public class NestedLinLerLayout extends LinearLayout implements NestedScrollingParent3 {
    private String TAG = NestedLinLerLayout.class.getSimpleName();
    private MyThiredLinLerLayout bottomThiredLinLerLayout;
    private MyViewModel myViewModel;
    public NestedLinLerLayout(Context context) {
        super(context);
    }

    public NestedLinLerLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public NestedLinLerLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    /**
     * @date 创建时间:2020/4/26 0026
     * @auther gaoxiaoxiong
     * @Descriptiion 设置底部bottom
     **/
    public void setThiredActivity(ThiredActivity thiredActivity){
        myViewModel = new ViewModelProvider.AndroidViewModelFactory(thiredActivity.getApplication()).create(MyViewModel.class);
        myViewModel.getMyThiredLinLerLayoutMutableLiveData().observe(thiredActivity, new Observer<MyThiredLinLerLayout>() {
            @Override
            public void onChanged(MyThiredLinLerLayout myThiredLinLerLayout) {
                bottomThiredLinLerLayout = myThiredLinLerLayout;
            }
        });
    }


    @Override
    public void onNestedScroll(@NonNull View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int type, @NonNull int[] consumed) {

    }

    @Override
    public boolean onStartNestedScroll(@NonNull View child, @NonNull View target, int axes, int type) {
        return (axes & ViewCompat.SCROLL_AXIS_VERTICAL) != -1;
    }

    @Override
    public void onNestedScrollAccepted(@NonNull View child, @NonNull View target, int axes, int type) {

    }

    @Override
    public void onStopNestedScroll(@NonNull View target, int type) {

    }

    @Override
    public void onNestedScroll(@NonNull View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int type) {

    }

    @Override
    public void onNestedPreScroll(@NonNull View target, int dx, int dy, @NonNull int[] consumed, int type) {

    }
}
