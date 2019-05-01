package com.io.choozo.custom;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by ayushsingla on 19/12/16.
 */

public class ExtraBoldTextView extends TextView {
    public ExtraBoldTextView(Context context) {
        super(context);
        init(context);
    }

    public ExtraBoldTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ExtraBoldTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    void init(Context context) {
        Typeface font = Typeface.createFromAsset(getContext().getAssets(), "fonts/seguibl.ttf");
        setTypeface(font);
    }

}
