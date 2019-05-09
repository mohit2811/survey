package com.app.survey;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.app.survey.adapters.NumbersAdapter;
import com.app.survey.answers.MultipleChoiceAnswer;
import com.app.survey.customs.OpenSansTextBold;
import com.app.survey.customs.OpenSansTextSemiBold;
import com.app.survey.datamodels.NumbersData;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MultipleChoiceQuestion extends AppCompatActivity {

    private LinearLayout answers_layout;

    private RecyclerView numbers_recycler;

    private int answer_count = 2;

    private ArrayList<NumbersData> numbers;

    private NumbersAdapter adapter;

    private EditText question ;

    private ProgressDialog pd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiple_choice_question);

        question = (EditText) findViewById(R.id.question_et);


        answers_layout = (LinearLayout) findViewById(R.id.answers_layout);

        numbers = new ArrayList<>();
        numbers_recycler = (RecyclerView) findViewById(R.id.numbers_recycler);
        numbers_recycler.setLayoutManager(new LinearLayoutManager(MultipleChoiceQuestion.this, LinearLayoutManager.HORIZONTAL, false));

        for (int i = 0; i < answer_count; i++) {
            NumbersData data = new NumbersData();
            data.setNumber(i+1);

            numbers.add(data);
        }
        adapter = new NumbersAdapter(numbers);

        numbers_recycler.setAdapter(adapter);

        pd = new ProgressDialog(MultipleChoiceQuestion.this);

        pd.setTitle("Loading");
        pd.setMessage("Please wait ..");


    }

    public void add_answer(View view) {

        create_answer_block();

    }

    private void create_answer_block() {

        EditText answer_et = new EditText(MultipleChoiceQuestion.this);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        params.setMargins(20, 20, 20, 20);
        answer_et.setLayoutParams(params);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            answer_et.setPaddingRelative(15, 15, 15, 15);
        } else {

            answer_et.setPadding(15, 15, 15, 15);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            answer_et.setBackground(getResources().getDrawable(R.drawable.stroke_rectangle));
        } else {
            answer_et.setBackgroundDrawable(getResources().getDrawable(R.drawable.stroke_rectangle));

        }
        answer_et.setHint("answer " + answer_count++);
        answer_et.setId(answer_count + 100);

        answers_layout.addView(answer_et);


        numbers.clear();
        for (int i = 0; i < answer_count; i++) {
            NumbersData data = new NumbersData();
            data.setNumber(i+1);
            numbers.add(data);
        }

        adapter.notifyDataSetChanged();

    }

    public void preview(View view) {

        if(validate_input()) {
            String[] answers = new String[answer_count];

            answers[0] = ((EditText) findViewById(R.id.answer1_et)).getText().toString();
            answers[1] = ((EditText) findViewById(R.id.answer2_et)).getText().toString();

            for (int i = 2; i < answer_count; i++) {

                answers[i] = ((EditText) findViewById(i + 1 + 100)).getText().toString();
            }

            Intent i = new Intent(MultipleChoiceQuestion.this, MultipleChoiceAnswer.class);

            i.putExtra("question", question.getText().toString());
            i.putExtra("answers", answers);
            i.putExtra("answers_allowed" , answers_allowed());

            startActivity(i);
            overridePendingTransition(R.anim.right_in, R.anim.left_out);
        }
    }


    private Boolean validate_input()
    {
        if(question.getText().toString().equals(""))
        {
            Toast.makeText(MultipleChoiceQuestion.this , "enter some question" , Toast.LENGTH_SHORT).show();

            return  false;
        }

        if(((EditText) findViewById(R.id.answer1_et)).getText().toString().equals(""))
        {
            Toast.makeText(MultipleChoiceQuestion.this , "enter answer one" , Toast.LENGTH_SHORT).show();

            return  false;
        }

        if(((EditText) findViewById(R.id.answer1_et)).getText().toString().equals(""))
        {
            Toast.makeText(MultipleChoiceQuestion.this , "enter answer two" , Toast.LENGTH_SHORT).show();

            return  false;
        }

        if(answers_allowed() == 0)
        {
            Toast.makeText(MultipleChoiceQuestion.this , "select number of answers" , Toast.LENGTH_SHORT).show();

            return  false;
        }

        return  true ;
    }

    private int answers_allowed()
    {
        int num_answers = 0 ;
        for ( int i = 0 ; i < numbers.size() ; i ++ )
        {
            if(numbers.get(i).getSelected())
            {
                num_answers = numbers.get(i).getNumber();
                return num_answers;
            }
        }

        return num_answers;
    }


    private void save_question()
    {

        pd.show();

        FirebaseDatabase database = FirebaseDatabase.getInstance();

        DatabaseReference reference = database.getReference("survey_questions");

        Map<String , Object> data = new HashMap<>();

        data.put("question" , question.getText().toString());





        data.put("option1" , ((EditText) findViewById(R.id.answer1_et)).getText().toString());
        data.put("option2" , ((EditText) findViewById(R.id.answer2_et)).getText().toString());

        for (int i = 2; i < answer_count; i++) {

            data.put("option"+i , ((EditText) findViewById(i + 1 + 100)).getText().toString());
        }

        data.put("type" , "multiple_type");
        data.put("answer_allowed" , answers_allowed());

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


    public void save(View view) {

        if(validate_input())
        {
            save_question();
        }
    }
}



