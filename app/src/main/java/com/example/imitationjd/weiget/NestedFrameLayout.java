package com.example.imitationjd.weiget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.NestedScrollingParent3;
import androidx.core.view.ViewCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.imitationjd.R;
import com.example.imitationjd.activity.ThiredActivity;
import com.example.imitationjd.viewmodel.MyViewModel;

/**
 * @date 创建时间:2020/4/26 0026
 * @auther gaoxiaoxiong
 * @Descriptiion 嵌套滚动
 **/
public class NestedFrameLayout extends FrameLayout implements NestedScrollingParent3 {
    private String TAG = NestedFrameLayout.class.getSimpleName();
    private RecyclerView rootRecyclerView;//根布局的recyclerview
    private MyThiredBottomLinLerLayout bottomThiredLinLerLayout;
    private RecyclerView childRecyclerView;//子recyclerview


    private MyViewModel myViewModel;

    public NestedFrameLayout(Context context) {
        super(context);
    }

    public NestedFrameLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public NestedFrameLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    /**
     * @date 创建时间:2020/4/26 0026
     * @auther gaoxiaoxiong
     * @Descriptiion 设置底部bottom
     **/
    public void setThiredActivity(ThiredActivity thiredActivity, RecyclerView recyclerView) {
        this.rootRecyclerView = recyclerView;
        myViewModel = new ViewModelProvider(thiredActivity.getViewModelStore(), ViewModelProvider.AndroidViewModelFactory.getInstance(thiredActivity.getApplication())).get(MyViewModel.class);
        myViewModel.getMyThiredLinLerLayoutMutableLiveData().observe(thiredActivity, new Observer<MyThiredBottomLinLerLayout>() {
            @Override
            public void onChanged(MyThiredBottomLinLerLayout myThiredBottomLinLerLayout) {
                bottomThiredLinLerLayout = myThiredBottomLinLerLayout;
            }
        });
        myViewModel.getSelected().observe(thiredActivity, new Observer<RecyclerView>() {
            @Override
            public void onChanged(RecyclerView recyclerView) {
                childRecyclerView = recyclerView;
                childRecyclerView.scrollToPosition(0);
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
        if (target instanceof RecyclerView) {
            if (dy > 0) {//手指向上
                if (bottomThiredLinLerLayout != null) {
                    if (bottomThiredLinLerLayout.getTop() != 0) {
                        rootRecyclerView.scrollBy(0, dy);
                    } else if (bottomThiredLinLerLayout.getTop() == 0) {
                        if (childRecyclerView != null) {
                            bottomThiredLinLerLayout.toDoNestedPreScroll(dy);
                            childRecyclerView.scrollBy(0, dy);
                            consumed[1] = dy;
                        }
                    }
                }else {
                    rootRecyclerView.scrollBy(0, dy);
                }
            }


            if (dy < 0) {//手指向下
                if (bottomThiredLinLerLayout!=null){
                    if (bottomThiredLinLerLayout.getTop() != 0) {
                        rootRecyclerView.scrollBy(0, dy);
                        consumed[1] = dy;
                    } else if (bottomThiredLinLerLayout.getTop() == 0) {
                        if (childRecyclerView != null) {
                            bottomThiredLinLerLayout.toDoNestedPreScroll(dy);
                            if (childRecyclerView.canScrollVertically(dy)) {
                                childRecyclerView.scrollBy(0, dy);
                                consumed[1] = dy;
                            } else {
                                rootRecyclerView.scrollBy(0, dy);
                            }
                        }
                    }
                }else {
                    rootRecyclerView.scrollBy(0, dy);
                }
            }

        }
    }
}
