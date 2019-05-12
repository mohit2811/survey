package com.app.survey.user;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.survey.R;
import com.app.survey.datamodels.ResponseDataModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ViewUserResponseDetails extends AppCompatActivity {


    List<ResponseDataModel> user_response_dates;

    RecyclerView recyclerView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_user_response_details);

        user_response_dates = new ArrayList<>();

        recyclerView = findViewById(R.id.recycler_view);

        recyclerView.setLayoutManager(new LinearLayoutManager(this , LinearLayoutManager.VERTICAL , false));


        get_all_responses();

    }

    private void get_all_responses()
    {
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        DatabaseReference reference = database.getReference("survey_response").child(getIntent().getStringExtra("email")).child("response").child(getIntent().getStringExtra("date"));

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot snapshot : dataSnapshot.getChildren())
                {

                    ResponseDataModel dataModel = snapshot.getValue(ResponseDataModel.class);

                    user_response_dates.add( dataModel );

                }


                recyclerView.setAdapter(new Adapter());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }




    private class ViewHolder extends RecyclerView.ViewHolder
    {


        TextView question , answer ;

        public ViewHolder(View itemView) {
            super(itemView);

            question = itemView.findViewById(R.id.question_id);

            answer = itemView.findViewById(R.id.answer_id);
        }
    }


    private class Adapter extends RecyclerView.Adapter<ViewHolder>
    {

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(ViewUserResponseDetails.this).inflate(R.layout.user_response_cell , parent , false));
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, final int position) {


            ResponseDataModel dataModel = user_response_dates.get(position);


            holder.question.setText( dataModel.question);

            holder.answer.setText( dataModel.answer );


        }

        @Override
        public int getItemCount()

        {
            return user_response_dates.size();
        }
    }
}
