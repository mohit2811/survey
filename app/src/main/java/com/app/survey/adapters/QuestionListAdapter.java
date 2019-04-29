package com.app.survey.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.survey.QuestionTypes;
import com.app.survey.R;
import com.app.survey.datamodels.QuestionListData;
import com.app.survey.holders.QuestionListHolder;

import java.util.ArrayList;

/**
 * Created by ghumman on 9/10/2017.
 */

public class QuestionListAdapter extends RecyclerView.Adapter<QuestionListHolder> {


    private ArrayList<QuestionListData> question_list ;
    private Context c ;

    public QuestionListAdapter(ArrayList<QuestionListData> question_list , Context c)
    {
        this.question_list = question_list ;
        this.c = c ;

    }

    @Override
    public QuestionListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new QuestionListHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.question_list_cell , parent , false));

    }

    @Override
    public void onBindViewHolder(QuestionListHolder holder, int position) {

        final QuestionListData data = question_list.get(position);

        holder.question_txt.setText(data.getQuestion_number());

        holder.question_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(data.getQuestion_number().equals("Add Question"))
                {

                    Intent i = new Intent(c , QuestionTypes.class);

                    c.startActivity(i);
                    ((Activity)c).overridePendingTransition(R.anim.right_in , R.anim.left_out);
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return question_list.size();
    }
}
