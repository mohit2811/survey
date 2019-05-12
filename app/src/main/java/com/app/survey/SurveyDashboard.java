package com.app.survey;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.app.survey.customs.OpenSansTextRegular;
import com.app.survey.datamodels.UserInfoDataModel;
import com.app.survey.user.ViewUserResponseActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SurveyDashboard extends AppCompatActivity {

    private RecyclerView all_users ;

    private List<UserInfoDataModel> user_list ;
    ProgressDialog pd ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey_dashboard);

        pd = new ProgressDialog(this);

        pd.setTitle("Loading");
        pd.setMessage("Please wait ..");

        all_users = findViewById(R.id.all_users);

        user_list = new ArrayList<>();

        all_users.setLayoutManager(new LinearLayoutManager(this , LinearLayoutManager.VERTICAL, false));


        get_data();

    }


    private void get_data()
    {
        pd.show();

        FirebaseDatabase database = FirebaseDatabase.getInstance();

        DatabaseReference reference = database.getReference("survey_user");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                pd.hide();

                for( DataSnapshot snapshot : dataSnapshot.getChildren())
                {
                    UserInfoDataModel data = snapshot.getValue(UserInfoDataModel.class);

                    user_list.add(data);
                }

                all_users.setAdapter(new Adapter());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }


    private class ViewHolder extends RecyclerView.ViewHolder{

        OpenSansTextRegular name , gender , contact ;

        LinearLayout user_cell ;

        public ViewHolder(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            gender = itemView.findViewById(R.id.gender);
            contact = itemView.findViewById(R.id.contact);

            user_cell = itemView.findViewById(R.id.user_cell);
        }
    }





    private class Adapter extends RecyclerView.Adapter<ViewHolder>
    {

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(SurveyDashboard.this).inflate(R.layout.all_user_cell , parent , false));
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {


            UserInfoDataModel dataModel = user_list.get(position);

            holder.name.setText(dataModel.name);
            holder.contact.setText(dataModel.contact);
            holder.gender.setText(dataModel.gender);

            holder.user_cell.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent i = new Intent(SurveyDashboard.this , ViewUserResponseActivity.class);
                    i.putExtra("user_id" , "");
                    startActivity(i);

                }
            });




        }

        @Override
        public int getItemCount() {

            return user_list.size();
        }
    }

}
