package com.app.survey.user;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.app.survey.R;

public class ThanksActvity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thanks_actvity);

        new Handler().postDelayed(
                new Runnable() {
                    @Override
                    public void run() {

                        finish();

                    }
                }, 3000
        );

    }



}
