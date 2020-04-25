package com.example.imitationjd.behavior.main;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.imitationjd.R;
import com.example.imitationjd.weiget.MyMainLinLerLayout;

public class MainMyFrameLayoutBehavior extends CoordinatorLayout.Behavior<FrameLayout> {
    private String TAG = MainMyFrameLayoutBehavior.class.getSimpleName();
    private MyMainLinLerLayout myMainLinLerLayout;
    private RecyclerView mainRecyclerView;
    public MainMyFrameLayoutBehavior() {
    }

    public MainMyFrameLayoutBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull FrameLayout child, @NonNull View directTargetChild, @NonNull View target, int axes, int type) {
        return (axes & ViewCompat.SCROLL_AXIS_VERTICAL) != -1;//判断是否为垂直滚动
    }

    @Override
    public void onNestedPreScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull FrameLayout child, @NonNull View target, int dx, int dy, @NonNull int[] consumed, int type) {
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed, type);
        if (myMainLinLerLayout == null) {
            myMainLinLerLayout = coordinatorLayout.findViewById(R.id.first_my_linlayout);
        }

        if (target instanceof RecyclerView) {
            float transY = child.getTranslationY() - dy;
            if (target.getId() == R.id.main_header_recyclerview) {
                RecyclerView recyclerView = (RecyclerView) target;
                mainRecyclerView = recyclerView;
                if (dy > 0) {//手指向上
                    if (recyclerView.canScrollVertically(dy)) {
                        recyclerView.scrollBy(0, dy);
                    } else {
                        if (child.getTranslationY() < -child.getHeight()) {
                            transY = -child.getHeight();
                        }
                        child.setTranslationY(transY);
                    }
                }

                if (dy < 0) {//手指向下
                    if (child.getTranslationY() == 0) {
                        child.setTranslationY(0);
                        recyclerView.scrollBy(0, dy);
                    } else {
                        if (child.getTranslationY() > -child.getHeight()) {
                            if (child.getTranslationY() > 0) {
                                child.setTranslationY(0);
                            } else {
                                if (child.getTranslationY() + transY > 0) {
                                    transY = 0;
                                }
                                consumed[1] = dy;
                                child.setTranslationY(transY);
                            }
                            if (child.getTranslationY() == 0) {
                                recyclerView.scrollBy(0, dy);
                            }
                        } else {
                            recyclerView.scrollBy(0, dy);
                        }
                    }
                }
            } else if (target.getId() == R.id.fragment_recyclerview) {
                if (dy > 0) {//手指向上
                    if (myMainLinLerLayout.getTranslationY() != 0) {
                        if (transY < -child.getHeight()) {
                            transY = -child.getHeight();
                        }
                        child.setTranslationY(transY);
                        consumed[1] = dy;
                    }
                }

                if (dy < 0) {//手指向下
                    if (transY > -child.getHeight() && transY < 0) {
                        child.setTranslationY(transY);
                    } else if (transY >0){
                        mainRecyclerView.scrollBy(0,dy);
                        child.setTranslationY(0);
                    }
                    consumed[1] = dy;
                }
            }
        }

        /*if (target instanceof RecyclerView) {
            if (target.getId() == R.id.main_header_recyclerview) {
                RecyclerView mainRecyclerView = (RecyclerView) target;
                float transY = child.getTranslationY() - dy;
                Log.e(TAG,"transY:"+transY);
                if (dy > 0 && !mainRecyclerView.canScrollVertically(dy)) {
                    child.setTranslationY(transY);
                } else if (dy < 0) {
                    if (child.getTranslationY() < 0) {
                        child.setTranslationY(transY);
                        consumed[1] = dy;
                    } else if (child.getTranslationY() > 0) {
                        child.setTranslationY(0);
                    }
                }
            } else if (target.getId() == R.id.fragment_recyclerview) {
                float transY = child.getTranslationY() - dy;
                Log.e(TAG,"transY:"+transY);
               if (myMainLinLerLayout.getTranslationY()!=0){
                   child.setTranslationY(transY);
                   consumed[1] = dy;
               }else if (myMainLinLerLayout.getTranslationY() == 0){
                   child.setTranslationY(transY);
               }
            }
        }*/
    }
}
