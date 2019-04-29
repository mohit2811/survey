package com.app.survey;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

public class ManageSurvey extends AppCompatActivity implements View.OnClickListener {

    private RelativeLayout survey_questions ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_survey);

        survey_questions = (RelativeLayout) findViewById(R.id.survey_questions);

        survey_questions.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        if(v.getId() == R.id.survey_questions)
        {
            Intent i = new Intent(ManageSurvey.this , QuestionsList.class);
            startActivity(i);

            overridePendingTransition(R.anim.right_in , R.anim.left_out);
        }
    }
}
