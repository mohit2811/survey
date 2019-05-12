package com.app.survey;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AdminLogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
    }

    public void login(View view) {

        EditText email_et = findViewById(R.id.email_et);

        EditText password_et = findViewById(R.id.password_et);


        String email = email_et.getText().toString();

        String password = password_et.getText().toString();


        if(email.equalsIgnoreCase("")|| password.equalsIgnoreCase(""))
        {
            Toast.makeText(AdminLogin.this , "fill all fields" , Toast.LENGTH_SHORT).show();

            return;
        }


        FirebaseAuth.getInstance().signInWithEmailAndPassword(email , password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful())
                {

                    Toast.makeText(AdminLogin.this , "Successfull" , Toast.LENGTH_SHORT).show();

                    startActivity(new Intent(AdminLogin.this , ManageSurvey.class));

                }

                else {

                    Toast.makeText(AdminLogin.this , "error" , Toast.LENGTH_SHORT).show();

                }
            }
        });

    }
}
