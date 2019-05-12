package com.app.survey;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class DisplayTextQuestion extends AppCompatActivity {


    private ProgressDialog pd;

    private EditText question_et ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_text_question);

        question_et = findViewById(R.id.question_et);

        pd = new ProgressDialog(this);
        pd.setTitle("Loading");
        pd.setMessage("Please wait ..");
    }


    public void save(View v)
    {

        if(question_et.getText().toString().trim().equalsIgnoreCase(""))
        {
            Toast.makeText(DisplayTextQuestion.this , "please enter text" , Toast.LENGTH_SHORT).show();

            return;
        }

        pd.show();

        FirebaseDatabase database = FirebaseDatabase.getInstance();

        DatabaseReference reference = database.getReference("survey_questions");

        Map<String , Object> data = new HashMap<>();

        data.put("question" , question_et.getText().toString());


        data.put("type" , "display");

        reference.push().setValue(data).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                pd.hide();

                if(task.isSuccessful())
                {
                    finish();

                }
            }
        });



    }



}
