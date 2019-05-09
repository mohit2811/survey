package com.app.survey.user;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.app.survey.R;
import com.app.survey.datamodels.UserInfoDataModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserInfoActivity extends AppCompatActivity {

    private EditText name_et , contact_et , email_et , dob_et , aniversary_et , gender_et ;

    private ProgressDialog pd ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_user_info);
        name_et = findViewById(R.id.name_et);
        contact_et = findViewById(R.id.contact_et);
        email_et = findViewById(R.id.email_et);
        dob_et = findViewById(R.id.dob_et);
        aniversary_et = findViewById(R.id.anniversary_et);
        gender_et = findViewById(R.id.gender_et);

        pd = new ProgressDialog(this);

        pd.setTitle("Loading");
        pd.setMessage("Please wait..");



    }

    public void next(View view) {

        String name = name_et.getText().toString();

        String contact = contact_et.getText().toString();

        String email = email_et.getText().toString();

        String dob = dob_et.getText().toString();

        String anniversary = aniversary_et.getText().toString();

        String gender = gender_et.getText().toString();


        if(name.trim().equalsIgnoreCase("") || contact.trim().equalsIgnoreCase("") || email.trim().equalsIgnoreCase("") || dob.trim().equalsIgnoreCase("") || gender.trim().equalsIgnoreCase(""))
        {
            Toast.makeText(UserInfoActivity.this , "please fill all fields" , Toast.LENGTH_SHORT).show();

            return;
        }



        FirebaseDatabase database = FirebaseDatabase.getInstance();

        DatabaseReference reference = database.getReference("survey_user").push();


        UserInfoDataModel dataModel = new UserInfoDataModel();

        dataModel.name = name;
        dataModel.contact = contact;
        dataModel.email = email;
        dataModel.dob = dob;
        dataModel.anniversary = anniversary;
        dataModel.gender = gender;

        reference.setValue(dataModel).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {


                if(task.isSuccessful())
                {

                    Toast.makeText(UserInfoActivity.this , "Information saved" , Toast.LENGTH_SHORT).show();

                    startActivity(new Intent(UserInfoActivity.this , UserShowQuestionsActivity.class));


                }

            }
        });



    }
}
