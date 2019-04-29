package com.app.survey.customs;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.Button;

/**
 * Created by ghumman on 9/16/2017.
 */

public class OpenSansButtonRegular extends android.support.v7.widget.AppCompatButton {
    public OpenSansButtonRegular(Context context) {
        super(context);
        init();
    }

    public OpenSansButtonRegular(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public OpenSansButtonRegular(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init() {
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "font/OpenSans-Regular.ttf");
        setTypeface(tf ,1);

    }
}
