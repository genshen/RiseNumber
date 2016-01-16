package com.holo.risenumber.views;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by cgs on 2016/1/15.
 */
public class RiseNumberTextView extends TextView implements Animator.AnimatorListener {
    private RiseListener mRiseListener = null;
    private Boolean RUNNING = false, riseInt = true;
    private float startValue = 0, endValue = 0;
    private int duration = 0, decimal = 2;

    public interface RiseListener {
        void onRiseEndFinish();
    }

    public RiseNumberTextView(Context context) {
        super(context);
    }

    public RiseNumberTextView(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
    }

    public RiseNumberTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    private void run() {
        RUNNING = true;
        ValueAnimator valueAnimator;
        if (riseInt) {
            valueAnimator = ValueAnimator.ofInt((int) startValue, (int) endValue);
            valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    setText(animation.getAnimatedValue().toString());
                }
            });
        } else {
            valueAnimator = ValueAnimator.ofFloat(startValue, endValue);
            valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    setText(FormatFloat(animation.getAnimatedValue().toString()));
                }
            });
        }
        valueAnimator.setDuration(duration);
        valueAnimator.addListener(this);
        valueAnimator.start();
    }


    @Override
    public void onAnimationRepeat(Animator animation) {

    }

    @Override
    public void onAnimationStart(Animator animation) {

    }

    @Override
    public void onAnimationEnd(Animator animation) {
        RUNNING = false;
        if (mRiseListener != null) {
            mRiseListener.onRiseEndFinish();
        }
    }

    @Override
    public void onAnimationCancel(Animator animation) {

    }

    private String FormatFloat(String s) {
        StringBuilder build = new StringBuilder(s);
        int index = s.lastIndexOf('.');
        int appendLength = decimal;
        if (index == -1) {
            build.append('.');
            index = s.length();
        } else {
            appendLength = decimal - (s.length() - index - 1);
        }
        for (int i = 0; i < appendLength; i++) {
            build.append('0');
        }
        return build.substring(0, index + decimal + 1);
    }

    /**
     * set start rise value
     *
     * @param value start value
     * @return this
     */
    public RiseNumberTextView setStartValue(float value) {
        this.startValue = value;
        return this;
    }

    /**
     * set ends rise value
     *
     * @param value ends value
     * @return this
     */
    public RiseNumberTextView setEndValue(float value) {
        this.endValue = value;
        return this;
    }

    /**
     * combination of function {@code setStartValue} and {@code setEndValue}
     *
     * @param startValue start value
     * @param endValue   end value
     * @return this
     */
    public RiseNumberTextView setRiseInterval(float startValue, float endValue) {
        this.startValue = startValue;
        this.endValue = endValue;
        return this;
    }

    /**
     * set duration of the rise Animator
     *
     * @param duration duration time
     * @return this
     */
    public RiseNumberTextView setDuration(int duration) {
        this.duration = duration;
        return this;
    }


    public RiseNumberTextView runInt(boolean INT) {
        if (!RUNNING)
            riseInt = INT;
        return this;
    }

    public RiseNumberTextView setDecimal(int decimal) {
        if (!RUNNING)
            this.decimal = decimal;
        return this;
    }

    public void start() {
        if (!RUNNING) {
            RUNNING = true;
            run();
        }
    }

    /**
     * set the callback function when rise ends
     *
     * @param callback callback
     */
    public void setOnRiseEndListener(RiseListener callback) {
        mRiseListener = callback;
    }
}
