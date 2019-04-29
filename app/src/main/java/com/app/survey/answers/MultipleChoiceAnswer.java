package com.app.survey.answers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


import com.app.survey.R;

import com.app.survey.customs.OpenSansTextSemiBold;

import org.apmem.tools.layouts.FlowLayout;

public class MultipleChoiceAnswer extends AppCompatActivity implements View.OnClickListener {

    private FlowLayout answers_layout ;

    private String [] answers;

    private OpenSansTextSemiBold question ;

    private boolean [] checked_answers ;

    int selected_answers_count = 0 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiple_choice_answer);

        answers_layout = (FlowLayout) findViewById(R.id.answers_layout);


        question = (OpenSansTextSemiBold) findViewById(R.id.question);


        answers = getIntent().getStringArrayExtra("answers");

        checked_answers = new boolean[answers.length];

        System.out.println(getIntent().getStringArrayExtra("answers").length);

        show_answers();
    }


    private void show_answers()
    {
        for (int i = 0 ; i < answers.length ; i++) {

            Button answer_btn = new Button(MultipleChoiceAnswer.this);
            FlowLayout.LayoutParams params = new FlowLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(20 , 20 , 20 , 20);

            answer_btn.setLayoutParams(params);
            answer_btn.setPadding(30, 30, 30, 30);
            answer_btn.setText(answers[i]);

            answer_btn.setId(i+100);
            answer_btn.setOnClickListener(this);
            answer_btn.setBackgroundDrawable(getResources().getDrawable(R.drawable.stroke_rectangle3));
            answer_btn.setTextColor(getResources().getColor(R.color.d_grey));

            answers_layout.addView(answer_btn);
        }


    }


    @Override
    public void onClick(View v)
    {

        int clicked_pos = v.getId() -100 ;

        if(checked_answers[clicked_pos])
        {
            Button answer_btn = (Button) findViewById(v.getId());
            answer_btn.setBackgroundDrawable(getResources().getDrawable(R.drawable.stroke_rectangle3));
            answer_btn.setTextColor(getResources().getColor(R.color.d_grey));
            checked_answers[clicked_pos] = false;
            selected_answers_count -= 1;
        }
        else
        {
            if(selected_answers_count < getIntent().getIntExtra("answers_allowed" , 0)) {

                Button answer_btn = (Button) findViewById(v.getId());
                answer_btn.setBackgroundDrawable(getResources().getDrawable(R.drawable.solid_rectangle));
                answer_btn.setTextColor(getResources().getColor(R.color.white));
                checked_answers[clicked_pos] = true;
                selected_answers_count += 1;
            }
            else {
                Toast.makeText(MultipleChoiceAnswer.this , "you can only select "+String.valueOf(selected_answers_count)+" answers",Toast.LENGTH_SHORT).show();
            }
        }

    }
}
