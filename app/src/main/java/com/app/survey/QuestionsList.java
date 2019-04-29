package com.app.survey;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.app.survey.adapters.QuestionListAdapter;
import com.app.survey.datamodels.QuestionListData;

import java.util.ArrayList;

public class QuestionsList extends AppCompatActivity {

    private RecyclerView questions_recycler ;
    private QuestionListAdapter adapter ;
    private ArrayList<QuestionListData> question_list ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions_list);

        question_list = new ArrayList<>();



        questions_recycler = (RecyclerView) findViewById(R.id.question_recycler);
        questions_recycler.setLayoutManager(new GridLayoutManager(this , 2 , LinearLayoutManager.VERTICAL , false));




    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);




    }

    @Override
    protected void onResume() {
        super.onResume();

        QuestionListData data = new QuestionListData();
        data.setQuestion_id("1");
        data.setQuestion_number("Add Question");

        question_list.add(data);
        adapter = new QuestionListAdapter(question_list , this);
        questions_recycler.setAdapter(adapter);
    }
}
