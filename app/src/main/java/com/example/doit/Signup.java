package com.example.doit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class Signup extends AppCompatActivity {

    private EditText mEmail, mPass;
    private Button signUpBtn;
    private TextView mTextView;

    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        mEmail = findViewById(R.id.email_reg);
        mPass = findViewById(R.id.passreg);
        mTextView = findViewById(R.id.textView);
        signUpBtn = findViewById(R.id.registration_btn);
        mAuth = FirebaseAuth.getInstance();

        mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Signup.this,Login.class));
            }
        });
        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createUser();
            }
        });

    }
    private  void createUser(){
        String email = mEmail.getText().toString();
        String pass = mPass.getText().toString();

        if(!email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            if(!pass.isEmpty()){
                mAuth.createUserWithEmailAndPassword(email,pass)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Toast.makeText(Signup.this, "registration success", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(Signup.this,Login.class));
                                finish();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Signup.this, "registration error", Toast.LENGTH_SHORT).show();
                    }
                });
            }else{
                mPass.setError("Empty Fields are not allowed");

            }
        }else if(email.isEmpty()){
            mEmail.setError("Empty Fields are not allowed");
        }else{
            mEmail.setError("Please enter correct email");
        }

    }
}