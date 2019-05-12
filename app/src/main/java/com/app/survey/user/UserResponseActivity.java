package com.app.survey.user;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.Toast;

import com.app.survey.R;
import com.app.survey.customs.OpenSansEditRegular;
import com.app.survey.customs.OpenSansTextRegular;
import com.app.survey.datamodels.UserQuestionDataModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserResponseActivity extends AppCompatActivity {

    private LinearLayout multi_question , two_choice_question , input_question , rating_question , sliding_question , satisfactory_question;


    private OpenSansTextRegular question1 , question2 , question3 , question4 , question5 , question6;

    private OpenSansEditRegular answer3;

    private List<UserQuestionDataModel> question_list ;

    private ProgressDialog pd ;

    private int curr_index = 0;

    private SeekBar seekBar_;

    private RatingBar ratingBar ;

    private RadioGroup two_option_group , satisfaction_group ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_response);

        pd = new ProgressDialog(this);

        pd.setMessage("Please wait ...");
        pd.setTitle("Loading");


        two_option_group = findViewById(R.id.two_option_group);

        satisfaction_group = findViewById(R.id.satisfaction_group);

        question_list = new ArrayList<>();

        seekBar_ = findViewById(R.id.seekbar_);

        ratingBar = findViewById(R.id.rating_bar);

        multi_question = findViewById(R.id.multi_question_);

        two_choice_question = findViewById(R.id.two_choices_question_);

        input_question = findViewById(R.id.text_input);

        rating_question = findViewById(R.id.rating_question);

        sliding_question = findViewById(R.id.sliding_question);

        satisfactory_question = findViewById(R.id.satisfaction_question);

        answer3 = findViewById(R.id.answer3);

        question1 = findViewById(R.id.question1);
        question2 = findViewById(R.id.question2);
        question3 = findViewById(R.id.question3);
        question4 = findViewById(R.id.question4);
        question5 = findViewById(R.id.question5);
        question6 = findViewById(R.id.question6);

        hide_all();

        get_all_questions();


    }

    public void next(View view) {


        get_answer();



    }


    private void get_all_questions()
    {
        pd.show();

        FirebaseDatabase database = FirebaseDatabase.getInstance();

        DatabaseReference reference = database.getReference("survey_questions");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                pd.hide();

                for(DataSnapshot snapshot : dataSnapshot.getChildren())
                {

                    UserQuestionDataModel data = snapshot.getValue(UserQuestionDataModel.class);

                    question_list.add(data);

                }


                set_question(curr_index);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void set_question(int i)
    {

        UserQuestionDataModel dataModel = question_list.get(i);


        if(dataModel.type.equalsIgnoreCase("two_type"))
        {

            hide_all();

            two_choice_question.setVisibility(View.VISIBLE);

            question2.setText(dataModel.question);


        }

        if(dataModel.type.equalsIgnoreCase("text_input"))
        {

            hide_all();

            input_question.setVisibility(View.VISIBLE);

            answer3.setText("");
            answer3.setInputType(InputType.TYPE_CLASS_NUMBER);

            question3.setText(dataModel.question);


        }

        if(dataModel.type.equalsIgnoreCase("email_input"))
        {

            hide_all();

            input_question.setVisibility(View.VISIBLE);

            answer3.setText("");

            answer3.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);

                question3.setText(dataModel.question);


        }

        if(dataModel.type.equalsIgnoreCase("rating"))
        {

            hide_all();

            rating_question.setVisibility(View.VISIBLE);

            question4.setText(dataModel.question);


        }

        if(dataModel.type.equalsIgnoreCase("sliding"))
        {

            hide_all();

            sliding_question.setVisibility(View.VISIBLE);

            question5.setText(dataModel.question);


        }

        if(dataModel.type.equalsIgnoreCase("satisfactory"))
        {

            hide_all();

            satisfactory_question.setVisibility(View.VISIBLE);

            question6.setText(dataModel.question);


        }

    }

    private void get_answer()

    {
        String answer = "";

        UserQuestionDataModel dataModel = question_list.get(curr_index);

        String question = dataModel.question;


        if(dataModel.type.equalsIgnoreCase("two_type") || dataModel.type.equalsIgnoreCase("multiple_type"))
        {

            if(two_option_group.getCheckedRadioButtonId() == R.id.option1)
            {
                answer = "yes";

            }

            else {

                answer = "no";
            }

        }

        if(dataModel.type.equalsIgnoreCase("text_input")  )
        {

           answer = answer3.getText().toString();


        }

        if(dataModel.type.equalsIgnoreCase("email_input"))
        {

            answer = answer3.getText().toString();


        }

        if(dataModel.type.equalsIgnoreCase("rating"))
        {

            answer = String.valueOf(ratingBar.getRating());


        }

        if(dataModel.type.equalsIgnoreCase("sliding"))
        {

            answer = String.valueOf(seekBar_.getProgress());


        }

        if(dataModel.type.equalsIgnoreCase("satisfactory"))
        {


            if(satisfaction_group.getCheckedRadioButtonId() == R.id.sad_option)
            {
                answer = "sad";
            }

            if(satisfaction_group.getCheckedRadioButtonId() == R.id.neutral_option)
            {
                answer = "neutral";
            }

            if(satisfaction_group.getCheckedRadioButtonId() == R.id.happy_option)
            {
                answer = "happy";
            }


        }

        save_response(question , answer);

    }


    private void save_response( String question , String answer )
    {
        pd.show();

        Date c = Calendar.getInstance().getTime();

        SimpleDateFormat curFormater = new SimpleDateFormat("dd-MM-yyyy");

        String newDateStr = curFormater.format(c);

        FirebaseDatabase database = FirebaseDatabase.getInstance();

        DatabaseReference reference = database.getReference("survey_response");

        Map<String , Object> map = new HashMap<>();

        map.put("question" , question );
        map.put("answer" , answer);

        reference.child(getIntent().getStringExtra("email")).child("response").child(newDateStr).push().setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                pd.hide();

                if(task.isSuccessful())
                {


                    curr_index ++;

                    if(curr_index == question_list.size())
                    {
                        startActivity(new Intent(UserResponseActivity.this , ThanksActvity.class));
                        finish();

                        return;
                    }

                    set_question(curr_index);

                }
            }
        });

    }


    private void hide_all()
    {

        multi_question.setVisibility(View.GONE);

        two_choice_question.setVisibility(View.GONE);

        input_question.setVisibility(View.GONE);

        rating_question.setVisibility(View.GONE);

        sliding_question.setVisibility(View.GONE);

        satisfactory_question.setVisibility(View.GONE);


    }


}
