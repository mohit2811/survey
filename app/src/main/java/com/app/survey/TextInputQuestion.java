package com.app.survey;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.app.survey.answers.MultipleChoiceAnswer;
import com.app.survey.answers.TextInputAnswer;
import com.app.survey.customs.OpenSansEditRegular;
import com.app.survey.customs.OpenSansTextRegular;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class TextInputQuestion extends AppCompatActivity implements View.OnClickListener {

    private OpenSansTextRegular single_line, multi_line;

    private boolean single_line_selected = true, multi_line_selected = false;

    private OpenSansEditRegular question_et , num_characters_et ;

    private ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_input_question);

        single_line = (OpenSansTextRegular) findViewById(R.id.single_line);
        multi_line = (OpenSansTextRegular) findViewById(R.id.multi_line);

        question_et = (OpenSansEditRegular) findViewById(R.id.question_et);
        num_characters_et = (OpenSansEditRegular) findViewById(R.id.num_characters_et);

        single_line.setOnClickListener(this);
        multi_line.setOnClickListener(this);

        pd = new ProgressDialog(TextInputQuestion.this);
        pd.setTitle("Loading");
        pd.setMessage("Please wait ..");

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.single_line:
                if (single_line_selected) {


                } else {
                    single_line.setBackgroundDrawable(getResources().getDrawable(R.drawable.solid_rectangle_left));
                    single_line.setTextColor(getResources().getColor(R.color.white));
                    single_line_selected = true;

                    multi_line.setBackgroundDrawable(null);
                    multi_line.setTextColor(getResources().getColor(R.color.colorPrimary));
                    multi_line_selected = false;
                }
                break;

            case R.id.multi_line:
                if (multi_line_selected) {

                } else {

                    single_line.setBackgroundDrawable(null);
                    single_line.setTextColor(getResources().getColor(R.color.colorPrimary));
                    single_line_selected = false;

                    multi_line.setBackgroundDrawable(getResources().getDrawable(R.drawable.solid_rectangle_right));
                    multi_line.setTextColor(getResources().getColor(R.color.white));
                    multi_line_selected = true;
                }

        }
    }

    public void preview(View view) {

        if(validate_input())
        {
            Intent i = new Intent(TextInputQuestion.this, TextInputAnswer.class);

            i.putExtra("question", question_et.getText().toString());
            i.putExtra("type", single_line_selected ? "single_line" : "multi_line");
            i.putExtra("characters_limit" , num_characters_et.getText().toString());

            startActivity(i);
            overridePendingTransition(R.anim.right_in, R.anim.left_out);
        }
    }

    private Boolean validate_input()
    {
        if(question_et.getText().toString().equals(""))
        {
            Toast.makeText(TextInputQuestion.this , "enter some question" , Toast.LENGTH_SHORT).show();

            return  false;
        }


        return  true ;
    }


    public void save(View view) {

        if(validate_input())
        {
            save_question();
        }

    }

    private void save_question()
    {

        pd.show();

        FirebaseDatabase database = FirebaseDatabase.getInstance();

        DatabaseReference reference = database.getReference("survey_questions");

        Map<String , Object> data = new HashMap<>();

        data.put("question" , question_et.getText().toString());





        data.put("characters_limit" , num_characters_et.getText().toString());

        data.put("type" , "text_input");

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
