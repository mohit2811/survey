package com.app.survey.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.survey.R;

/**
 * Created by ghumman on 9/10/2017.
 */

public class QuestionListHolder extends RecyclerView.ViewHolder {

    public TextView question_txt ;
    public LinearLayout question_link ;

    public QuestionListHolder(View itemView) {
        super(itemView);

        this.question_txt = (TextView) itemView.findViewById(R.id.question_txt);
        this.question_link = (LinearLayout) itemView.findViewById(R.id.question_link);

    }
}
