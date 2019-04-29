package com.app.survey.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.app.survey.R;
import com.app.survey.customs.OpenSansTextSemiBold;

/**
 * Created by ghumman on 9/11/2017.
 */

public class NumbersHolder extends RecyclerView.ViewHolder {

    public OpenSansTextSemiBold number ;

    public NumbersHolder(View itemView) {
        super(itemView);

        number = (OpenSansTextSemiBold) itemView.findViewById(R.id.number);
    }
}
