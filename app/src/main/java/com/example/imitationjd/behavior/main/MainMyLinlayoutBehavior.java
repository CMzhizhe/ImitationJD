package com.example.imitationjd.behavior.main;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.example.imitationjd.R;
import com.example.imitationjd.weiget.MyMainLinLerLayout;

public class MainMyLinlayoutBehavior extends CoordinatorLayout.Behavior<MyMainLinLerLayout> {
    private String TAG = MainMyLinlayoutBehavior.class.getSimpleName();
    private int titleHeight = 0;

    public MainMyLinlayoutBehavior() {
    }

    public MainMyLinlayoutBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(@NonNull CoordinatorLayout parent, @NonNull MyMainLinLerLayout child, @NonNull View dependency) {
        return dependency instanceof FrameLayout;
    }

    @Override
    public boolean onDependentViewChanged(@NonNull CoordinatorLayout parent, @NonNull MyMainLinLerLayout child, @NonNull View dependency) {
        if (titleHeight == 0) {
            titleHeight = child.findViewById(R.id.ctl_first_layout).getHeight();
        }
        FrameLayout frameLayout = (FrameLayout) dependency;
        float transY = frameLayout.getHeight() + frameLayout.getTranslationY() / frameLayout.getHeight() * frameLayout.getHeight();
        if (transY < 0) {
            transY = 0;
        }
        child.setTranslationY(transY);
        return true;
    }
}
