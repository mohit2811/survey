package com.app.survey;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class QuestionTypes extends AppCompatActivity implements View.OnClickListener {

    private LinearLayout two_choices_question , multiple_choice_question , text_input_question ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_types);

        two_choices_question = (LinearLayout) findViewById(R.id.two_choices_question);
        multiple_choice_question = (LinearLayout) findViewById(R.id.multipe_choice);
        text_input_question = (LinearLayout) findViewById(R.id.text_input_question);

        multiple_choice_question.setOnClickListener(this);
        two_choices_question.setOnClickListener(this);
        text_input_question.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        Intent i = null;

        switch (v.getId())
        {
            case R.id.two_choices_question:
               i = new Intent(QuestionTypes.this , TwoChoiceQuestion.class);
                startActivity(i);
                overridePendingTransition(R.anim.right_in , R.anim.left_out);
                break;
            case R.id.multipe_choice:
                i = new Intent(QuestionTypes.this , MultipleChoiceQuestion.class);
                startActivity(i);
                overridePendingTransition(R.anim.right_in , R.anim.left_out);
                break;

            case R.id.text_input_question:
                i = new Intent(QuestionTypes.this , TextInputQuestion.class);
                startActivity(i);
                overridePendingTransition(R.anim.right_in , R.anim.left_out);
                break;

        }
    }
}
