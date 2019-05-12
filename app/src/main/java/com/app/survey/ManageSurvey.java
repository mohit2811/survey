package com.app.survey;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ManageSurvey extends AppCompatActivity implements View.OnClickListener {

    private RelativeLayout survey_questions ;

    public static float males= 0 , females = 0;

    ProgressDialog pd ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_survey);

        pd = new ProgressDialog(this);
        pd.setTitle("Loading");
        pd.setMessage("Please wait");

        survey_questions = (RelativeLayout) findViewById(R.id.survey_questions);

        survey_questions.setOnClickListener(this);

        males = 0;

        females = 0;

        get_user_percentage();

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

    public void open_dashboard(View view) {

        Intent i = new Intent(ManageSurvey.this , SurveyDashboard.class);
        startActivity(i);

        overridePendingTransition(R.anim.right_in , R.anim.left_out);

    }

    public void open_help(View view) {


        Intent i = new Intent(ManageSurvey.this , HelpActivity.class);
        startActivity(i);

        overridePendingTransition(R.anim.right_in , R.anim.left_out);

    }

    public void open_analytics(View view) {


        Intent i = new Intent(ManageSurvey.this , AnalyticsActivity.class);
        startActivity(i);

        overridePendingTransition(R.anim.right_in , R.anim.left_out);
    }

    private void get_user_percentage() {

        pd.show();

        FirebaseDatabase database = FirebaseDatabase.getInstance();

        DatabaseReference reference = database.getReference("survey_user");

        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                pd.hide();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    if (snapshot.child("gender").exists()) {
                        if (snapshot.child("gender").getValue().toString().equalsIgnoreCase("male")) {
                            males++;
                        }

                        if (snapshot.child("gender").getValue().toString().equalsIgnoreCase("female")) {
                            females++;
                        }

                    }

                }



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}
