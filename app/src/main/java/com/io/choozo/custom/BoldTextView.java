package com.io.choozo.custom;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by ayushsingla on 19/12/16.
 */

public class BoldTextView extends TextView {
    public BoldTextView(Context context) {
        super(context);
        init(context);
    }

    public BoldTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public BoldTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    void init(Context context) {
        Typeface font = Typeface.createFromAsset(getContext().getAssets(), "fonts/segoeuib.ttf");
        setTypeface(font);
    }

}
