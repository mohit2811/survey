package com.app.survey.customs;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by ghumman on 9/10/2017.
 */

public class OpenSansTextBold extends android.support.v7.widget.AppCompatTextView {

    public OpenSansTextBold(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public OpenSansTextBold(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public OpenSansTextBold(Context context) {
        super(context);
        init();
    }

    public void init() {
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "font/OpenSans-Bold.ttf");
        setTypeface(tf ,1);

    }

}