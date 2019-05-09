package com.app.survey;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.app.survey.user.UserInfoActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private RelativeLayout manage_survey_link , start_survey_link ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        manage_survey_link = (RelativeLayout) findViewById(R.id.manage_survey_link);
        start_survey_link = (RelativeLayout) findViewById(R.id.start_survey_link);

        manage_survey_link.setOnClickListener(this);
        start_survey_link.setOnClickListener(this);


    }

    @Override
    public void onClick(View v)
    {

        switch (v.getId())
        {
            case R.id.manage_survey_link:
                Intent i = new Intent(MainActivity.this , ManageSurvey.class);
                startActivity(i);

                overridePendingTransition(R.anim.right_in , R.anim.left_out);

                break;

            case R.id.start_survey_link:
                Intent i2 = new Intent(MainActivity.this , UserInfoActivity.class);
                startActivity(i2);

                overridePendingTransition(R.anim.left_in , R.anim.right_out);

                break;

        }


    }
}
