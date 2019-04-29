package com.app.survey.customs;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

/**
 * Created by ghumman on 9/10/2017.
 */

public class OpenSansTextSemiBold extends android.support.v7.widget.AppCompatTextView {

    public OpenSansTextSemiBold(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public OpenSansTextSemiBold(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public OpenSansTextSemiBold(Context context) {
        super(context);
        init();
    }

    public void init() {
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "font/OpenSans-SemiBold.ttf");
        setTypeface(tf ,1);

    }

}