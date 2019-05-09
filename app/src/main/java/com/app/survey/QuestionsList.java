package com.app.survey;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.app.survey.adapters.QuestionListAdapter;
import com.app.survey.datamodels.QuestionListData;
import com.app.survey.datamodels.QuestionNameDataModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class QuestionsList extends AppCompatActivity {

    private RecyclerView questions_recycler ;
    private QuestionListAdapter adapter ;
    private ArrayList<QuestionNameDataModel> question_list ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions_list);

        question_list = new ArrayList<>();

        questions_recycler = (RecyclerView) findViewById(R.id.question_recycler);
        questions_recycler.setLayoutManager(new LinearLayoutManager(this ,  LinearLayoutManager.VERTICAL , false));

        get_all_questions();

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

    }

    @Override
    protected void onResume() {
        super.onResume();


    }


    private void get_all_questions()
    {
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        DatabaseReference reference = database.getReference("survey_questions");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot snapshot : dataSnapshot.getChildren())
                {

                    QuestionNameDataModel data = snapshot.getValue(QuestionNameDataModel.class);

                    question_list.add(data);

                }

                adapter = new QuestionListAdapter(question_list , QuestionsList.this);


                questions_recycler.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public void add_question(View view) {

        Intent i = new Intent(QuestionsList.this , QuestionTypes.class);

        startActivity(i);
        overridePendingTransition(R.anim.right_in , R.anim.left_out);
    }
}
