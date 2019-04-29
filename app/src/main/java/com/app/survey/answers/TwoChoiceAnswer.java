package com.app.survey.answers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.app.survey.R;

import org.w3c.dom.Text;

public class TwoChoiceAnswer extends AppCompatActivity {

    private TextView question , option1 , option2 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two_choice_answer);

        question = (TextView) findViewById(R.id.question);

        option1 = (TextView) findViewById(R.id.option1);

        option2 = (TextView) findViewById(R.id.option2);

        if(getIntent().getStringExtra("question") != null)
        {
            question.setText(getIntent().getStringExtra("question"));
            option1.setText(getIntent().getStringExtra("option1"));

            option2.setText(getIntent().getStringExtra("option2"));
        }
    }
}
