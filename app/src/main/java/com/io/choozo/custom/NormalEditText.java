package com.io.choozo.custom;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * Created by ayushsingla on 19/12/16.
 */

public class NormalEditText extends EditText {
    public NormalEditText(Context context) {
        super(context);
        init(context);
    }

    public NormalEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public NormalEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    void init(Context context) {
        Typeface font = Typeface.createFromAsset(getContext().getAssets(), "fonts/segoeui.ttf");
        setTypeface(font);
    }

}
