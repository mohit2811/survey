package com.app.survey.customs;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * Created by ghumman on 9/16/2017.
 */

public class OpenSansEditRegular extends android.support.v7.widget.AppCompatEditText {
    public OpenSansEditRegular(Context context) {
        super(context);
        init();
    }

    public OpenSansEditRegular(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public OpenSansEditRegular(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    public void init() {
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "font/OpenSans-Regular.ttf");
        setTypeface(tf ,1);

    }
}
