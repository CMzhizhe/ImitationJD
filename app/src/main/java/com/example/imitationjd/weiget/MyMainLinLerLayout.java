package com.example.imitationjd.weiget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.NestedScrollingParent3;
import androidx.core.view.ViewCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.imitationjd.R;
import com.example.imitationjd.viewmodel.MyViewModel;

/**
 * @date 创建时间:2020/4/24 0024
 * @auther gaoxiaoxiong
 * @Descriptiion 用于main activity
 **/
public class MyMainLinLerLayout extends LinearLayout implements NestedScrollingParent3 {
    private String TAG = MyMainLinLerLayout.class.getSimpleName();
    private RecyclerView currentRecyclerView;
    private MyViewModel myViewModel;
    private boolean isDescExOpen = true;
    private Context mContext;
    private LinearLayout titleDescLayout;
    private boolean animatorIsRunning = false;

    public void setFirstActivity(AppCompatActivity firstActivity) {
        myViewModel = new ViewModelProvider(firstActivity.getViewModelStore(), ViewModelProvider.AndroidViewModelFactory.getInstance(firstActivity.getApplication())).get(MyViewModel.class);
        myViewModel.getSelected().observe(firstActivity, new Observer<RecyclerView>() {
            @Override
            public void onChanged(RecyclerView recyclerView) {
                currentRecyclerView = recyclerView;
                currentRecyclerView.scrollToPosition(0);
            }
        });
    }


    public MyMainLinLerLayout(Context context) {
        super(context);
        this.mContext = context;
    }

    public MyMainLinLerLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
    }

    public MyMainLinLerLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
    }

    @Override
    public void onNestedScroll(@NonNull View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int type, @NonNull int[] consumed) {

    }

    @Override
    public boolean onStartNestedScroll(@NonNull View child, @NonNull View target, int axes, int type) {
        return (axes & ViewCompat.SCROLL_AXIS_VERTICAL) != -1 && this.getTranslationY() == 0;
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
        if (titleDescLayout == null) {
            for (int i = 0; i < getChildCount(); i++) {
                View view = getChildAt(i);
                if (view.getId() == R.id.ctl_first_layout) {
                    titleDescLayout = view.findViewById(R.id.ll_first_title_desc);
                    break;
                }
            }
        }

        if (dy > 0) {//手指向上
            if (isDescExOpen && !animatorIsRunning) {
                animatorIsRunning = true;
                ValueAnimator valueAnimator = ValueAnimator.ofFloat(1.0f, 0.1f);
                valueAnimator.setDuration(800);
                valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        float value = (float) animation.getAnimatedValue();
                        ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) titleDescLayout.getLayoutParams();
                        layoutParams.height = (int) (getTitleDescHeight() * value);
                        titleDescLayout.setLayoutParams(layoutParams);
                    }
                });
                valueAnimator.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        isDescExOpen = false;
                        animatorIsRunning = false;
                    }
                });
                valueAnimator.start();
            }
        }


        if (dy < 0 && !currentRecyclerView.canScrollVertically(dy)) {//手指向下
            if (!isDescExOpen && !animatorIsRunning) {
                animatorIsRunning = true;
                ValueAnimator valueAnimator = ValueAnimator.ofFloat(0.1f, 1.0f);
                valueAnimator.setDuration(800);
                valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        float value = (float) animation.getAnimatedValue();
                        ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) titleDescLayout.getLayoutParams();
                        layoutParams.height = (int) (getTitleDescHeight() * value);
                        titleDescLayout.setLayoutParams(layoutParams);
                    }
                });
                valueAnimator.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        isDescExOpen = true;
                        animatorIsRunning = false;
                    }
                });
                valueAnimator.start();
            }
            consumed[1] = dy;
        }

    }

    private int getTitleDescHeight() {
        return mContext.getResources().getDimensionPixelOffset(R.dimen.first_title_desc);
    }
}
