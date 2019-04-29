package com.app.survey;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.app.survey.answers.MultipleChoiceAnswer;
import com.app.survey.answers.TextInputAnswer;
import com.app.survey.customs.OpenSansEditRegular;
import com.app.survey.customs.OpenSansTextRegular;

public class TextInputQuestion extends AppCompatActivity implements View.OnClickListener {

    private OpenSansTextRegular single_line, multi_line;

    private boolean single_line_selected = true, multi_line_selected = false;

    private OpenSansEditRegular question_et , num_characters_et ;

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
}
