package com.example.imitationjd.behavior.main;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import com.example.imitationjd.weiget.MyLinLerLayout;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

public class MainMyLinlayoutBehavior extends CoordinatorLayout.Behavior<MyLinLerLayout> {
    private boolean isFirstLoad = true;
    public MainMyLinlayoutBehavior() {
    }

    public MainMyLinlayoutBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(@NonNull CoordinatorLayout parent, @NonNull MyLinLerLayout child, @NonNull View dependency) {
        return dependency instanceof FrameLayout;
    }

    @Override
    public boolean onDependentViewChanged(@NonNull CoordinatorLayout parent, @NonNull MyLinLerLayout child, @NonNull View dependency) {
        FrameLayout frameLayout = (FrameLayout) dependency;
        if (isFirstLoad){
            isFirstLoad = false;
            child.setY(frameLayout.getHeight());
        }
        return true;
    }
}
