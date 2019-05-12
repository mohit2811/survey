package com.app.survey.user;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.app.survey.R;
import com.app.survey.datamodels.UserInfoDataModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class UserInfoActivity extends AppCompatActivity {

    private EditText name_et , contact_et , email_et , dob_et , aniversary_et , gender_et ;

    private ProgressDialog pd ;

    final Calendar myCalendar = Calendar.getInstance();



    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            // TODO Auto-generated method stub
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        }

    };

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


        dob_et.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new DatePickerDialog(UserInfoActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();

            }
        });





    }

    private void updateLabel() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        dob_et.setText(sdf.format(myCalendar.getTime()));
    }



    public void next(View view) {

        String name = name_et.getText().toString();

        String contact = contact_et.getText().toString();

        final String email = email_et.getText().toString();

        String dob = dob_et.getText().toString();

        String anniversary = aniversary_et.getText().toString();

        String gender = gender_et.getText().toString();


        if(name.trim().equalsIgnoreCase("") || contact.trim().equalsIgnoreCase("") || email.trim().equalsIgnoreCase("") || dob.trim().equalsIgnoreCase("") || gender.trim().equalsIgnoreCase(""))
        {
            Toast.makeText(UserInfoActivity.this , "please fill all fields" , Toast.LENGTH_SHORT).show();

            return;
        }



        FirebaseDatabase database = FirebaseDatabase.getInstance();

        DatabaseReference reference = database.getReference("survey_user").child(email.replace(".",""));


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

                    Intent i = new Intent(UserInfoActivity.this , UserResponseActivity.class);

                    i.putExtra("email" , email.replace("." , ""));

                    startActivity(i);

                    finish();



                }

            }
        });



    }
}
