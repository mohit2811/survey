package com.app.survey;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.app.survey.answers.TwoChoiceAnswer;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class TwoChoiceQuestion extends AppCompatActivity {

    private EditText question_et , option1_et , option2_et ;

    private ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two_choice_question);

        question_et = (EditText) findViewById(R.id.question_et);

        option1_et = (EditText) findViewById(R.id.option1_et);

        option2_et = (EditText) findViewById(R.id.option2_et);

        pd = new ProgressDialog(TwoChoiceQuestion.this);

        pd.setTitle("Loading");
        pd.setMessage("Please wait ..");


    }

    public void preview(View view) {

        if(validate_input())
        {
            Intent i = new Intent(TwoChoiceQuestion.this , TwoChoiceAnswer.class);
            i.putExtra("question" , question_et.getText().toString());
            i.putExtra("option1" , option1_et.getText().toString());
            i.putExtra("option2" , option2_et.getText().toString());

            startActivity(i);
            overridePendingTransition(R.anim.right_in , R.anim.left_out);
        }

    }

    public void save(View view) {

        if(validate_input())
        {
            save_question();
        }
    }


    private Boolean validate_input()
    {
        if(question_et.getText().toString().equals(""))
        {
            Toast.makeText(TwoChoiceQuestion.this , "enter some question" , Toast.LENGTH_SHORT).show();

            return  false;
        }

        if(option1_et.getText().toString().equals(""))
        {
            Toast.makeText(TwoChoiceQuestion.this , "enter option one" , Toast.LENGTH_SHORT).show();

            return  false;
        }

        if(option2_et.getText().toString().equals(""))
        {
            Toast.makeText(TwoChoiceQuestion.this , "enter option two" , Toast.LENGTH_SHORT).show();

            return  false;
        }

        return  true ;
    }

    private void save_question()
    {

        pd.show();

        FirebaseDatabase database = FirebaseDatabase.getInstance();

        DatabaseReference reference = database.getReference("survey_questions");

        Map<String , Object> data = new HashMap<>();

        data.put("question" , question_et.getText().toString());
        data.put("option1" , option1_et.getText().toString());
        data.put("option2" , option2_et.getText().toString());
        data.put("type" , "two_type");

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
