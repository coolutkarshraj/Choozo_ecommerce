package com.io.choozo.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.CalendarView;
import android.widget.RelativeLayout;

import com.fifo.R;
import com.fifo.helpers.K;
import com.fifo.helpers.calendar.OnCalendarAnimListener;
import com.fifo.helpers.calendar.OnDateSelectedListener;

/**
 * Created by ayushsingla on 07/01/17.
 */

public class DefaultCalendarView extends RelativeLayout {

    public DefaultCalendarView(Context context) {
        super(context);
        init(context);
    }

    public DefaultCalendarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public DefaultCalendarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    View v;

    Animation slideDown, slideUp;
    CalendarView cvCalendar;
    View vShadow;

    public void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        v = inflater.inflate(R.layout.default_calendar, this);
        slideDown = AnimationUtils.loadAnimation(context, R.anim.slide_down);
        slideUp = AnimationUtils.loadAnimation(context, R.anim.slide_up);

        initailse(context);
        setValues(context);
        setListeners(context);
    }

    public void initailse(Context context) {
        cvCalendar = (CalendarView) v.findViewById(R.id.cvCalendar);
        vShadow = v.findViewById(R.id.vShadow);
    }

    public void setValues(Context context) {

    }

    public void setListeners(Context context) {
        vShadow.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    hide();
                }
                return true;
            }
        });
    }

    public void show(final OnCalendarAnimListener animListener) {
        setVisibility(View.VISIBLE);
        startAnimation(slideDown);
        slideDown.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                animListener.onAnimComplete();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    public void hide(final OnCalendarAnimListener animListener) {
        startAnimation(slideUp);
        slideUp.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                setVisibility(View.GONE);
                animListener.onAnimComplete();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    public void show() {
        setVisibility(View.VISIBLE);
        startAnimation(slideDown);
    }

    public void hide() {
        startAnimation(slideUp);
        slideUp.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    public boolean isVisible() {
        return getVisibility() == View.VISIBLE;
    }

    public void setOnDateSelectedListener(final OnDateSelectedListener onDateSelectedListener) {
        cvCalendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView calendarView, int year, int month, int day) {
                onDateSelectedListener.onDateSelected(year, month, day);
                if (isVisible()) {
                    hide();
                }
            }
        });
    }

    public void setMinDate(long millis) {
        cvCalendar.setMinDate(millis);
    }

    public void setMaxDate(long millis) {
        cvCalendar.setMaxDate(millis);
        K.Log.i("TAG", "max: " + cvCalendar.getMaxDate());
    }

    public void setDate(long millis) {
        cvCalendar.setDate(millis);
    }
}
