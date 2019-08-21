package com.feel.horizontalscrollview.behavior;

import android.animation.Animator;
import android.content.Context;
import android.os.Build;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.animation.Interpolator;

import com.feel.horizontalscrollview.BuildConfig;
import com.feel.horizontalscrollview.R;


public class QuickReturnBehavior extends CoordinatorLayout.Behavior<View> {
    private static final Interpolator INTERPOLATOR = new FastOutSlowInInterpolator();
    private static final long ANIMATION_DURATION = 1000;
    private int dyDirectionSum;
    private boolean isShowing;
    private boolean isHiding;

    public QuickReturnBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }



    @Override
    public boolean onDependentViewChanged(@NonNull CoordinatorLayout parent, @NonNull View child, @NonNull View dependency) {
        return super.onDependentViewChanged(parent, child, dependency);
    }

    @Override
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child, @NonNull View directTargetChild, @NonNull View target, int axes, int type) {
        return axes == ViewCompat.SCROLL_AXIS_VERTICAL;
    }

    @Override
    public void onNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child, @NonNull View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int type) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, type);
    }

    @Override
    public void onNestedPreScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child, @NonNull View target, int dx, int dy, @NonNull int[] consumed, int type) {
        if(dy < 0) {
            showView(child);
        }
        else {
            hideView(child);
        }
        if(BuildConfig.DEBUG) {
            Log.e("Scroll",String.valueOf("dy : "+dy));
        }
    }


    private void showView(final View view) {
        if (isShowing || view.getVisibility() == View.VISIBLE) {
            return;
        }
        ViewPropertyAnimator animator = view.animate().translationY(0).setInterpolator(INTERPOLATOR).setDuration(ANIMATION_DURATION);
        animator.setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
                isShowing = true;
                view.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                isShowing = false;
            }

            @Override
            public void onAnimationCancel(Animator animator) {
                // 취소되면 다시 숨김
                isShowing = false;
//                hideView(view);

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });


    }

    private void hideView(final View view) {
        if (isHiding || view.getVisibility() != View.VISIBLE) {
            return;
        }
        ViewPropertyAnimator animator;
        if (view.getId() == R.id.tv_header) {
            animator = view.animate().translationY(-view.getHeight()).setInterpolator(INTERPOLATOR).setDuration(ANIMATION_DURATION);
        } else {
            animator = view.animate().translationY(view.getHeight()).setInterpolator(INTERPOLATOR).setDuration(ANIMATION_DURATION);
        }

        animator.setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
                isHiding = true;
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                isHiding = false;
                view.setVisibility(View.INVISIBLE);

            }

            @Override
            public void onAnimationCancel(Animator animator) {
                // 취소되면 다시 보여줌
                isHiding = false;
//                showView(view);
            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
    }
}
