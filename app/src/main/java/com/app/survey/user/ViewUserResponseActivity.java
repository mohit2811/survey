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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ViewUserResponseActivity extends AppCompatActivity {


    List<String> user_response_dates;

    List<String> user_emails;

    RecyclerView recyclerView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_user_response);

        user_response_dates = new ArrayList<>();

        user_emails = new ArrayList<>();

        recyclerView = findViewById(R.id.recycler_view);

        recyclerView.setLayoutManager(new LinearLayoutManager(ViewUserResponseActivity.this , LinearLayoutManager.VERTICAL , false));


        get_all_dates();

    }



    private void get_all_dates()
    {
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        DatabaseReference reference = database.getReference("survey_response");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot snapshot : dataSnapshot.getChildren())
                {

                    for(DataSnapshot snapshot1 : snapshot.getChildren())
                    {

                        for(DataSnapshot snapshot2 : snapshot1.getChildren())
                        {
                            if(snapshot2.exists()) {

                                user_response_dates.add(snapshot2.getKey());

                                user_emails.add(snapshot.getKey());

                            }
                        }
                    }


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


        TextView date ;

        public ViewHolder(View itemView) {
            super(itemView);

            date = itemView.findViewById(R.id.date_txt);
        }
    }


    private class Adapter extends RecyclerView.Adapter<ViewHolder>
    {

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(ViewUserResponseActivity.this).inflate(R.layout.user_response_date_cell , parent , false));
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, final int position) {

            holder.date.setText(user_response_dates.get(position));

            holder.date.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent i = new Intent(ViewUserResponseActivity.this , ViewUserResponseDetails.class);

                    i.putExtra("date" , user_response_dates.get(position));
                    i.putExtra("email" , user_emails.get(position));

                    startActivity(i);

                }
            });


        }

        @Override
        public int getItemCount()

        {
            return user_response_dates.size();
        }
    }

}
