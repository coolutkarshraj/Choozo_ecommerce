package com.io.choozo.custom;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.Button;

/**
 * Created by ayushsingla on 19/12/16.
 */

public class ExtraBoldButton extends Button {
    public ExtraBoldButton(Context context) {
        super(context);
        init(context);
    }

    public ExtraBoldButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ExtraBoldButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    void init(Context context) {
        Typeface font = Typeface.createFromAsset(getContext().getAssets(), "fonts/seguibl.ttf");
        setTypeface(font);
    }

}
