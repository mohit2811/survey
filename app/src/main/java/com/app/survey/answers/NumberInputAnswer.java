package com.app.survey.answers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;

import com.app.survey.R;
import com.app.survey.customs.OpenSansEditRegular;
import com.app.survey.customs.OpenSansTextRegular;

public class NumberInputAnswer extends AppCompatActivity {

    private OpenSansEditRegular answer_et ;

    private OpenSansTextRegular question ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number_input_answer);

        question = (OpenSansTextRegular) findViewById(R.id.question);

        answer_et = (OpenSansEditRegular) findViewById(R.id.answer_et);

        if(getIntent().getStringExtra("type").equals("single_line"))
        {
            answer_et.setSingleLine();
        }
        else {
            answer_et.setInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE);
        }

        question.setText(getIntent().getStringExtra("question"));
    }
}
