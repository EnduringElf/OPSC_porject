package com.example.opsc_porject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;

public class RegistrationActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView Banner, RegisterUser;
    private EditText EditTextName;
    private EditText EditTextAge;
    private EditText EditTextEmail;
    private EditText EditTextPassword;
    private ProgressBar progressBar;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        mAuth = FirebaseAuth.getInstance();

        Banner = (TextView) findViewById(R.id.Banner);
        Banner.setOnClickListener(this);

        RegisterUser = (Button) findViewById(R.id.RegisterBTN);
        RegisterUser.setOnClickListener(this);

        EditTextName = (EditText) findViewById(R.id.RegName);
        EditTextAge = (EditText) findViewById(R.id.RegAge);
        EditTextEmail = (EditText) findViewById(R.id.RegEmail);
        EditTextPassword = (EditText) findViewById(R.id.RegPassword);

        progressBar = (ProgressBar) findViewById(R.id.RegProgressbar);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.Banner:
                startActivity(new Intent(this, SignInActivity.class));
                break;
            case R.id.RegisterBTN:
                registerUser();
                break;
        }

    }

    private void registerUser() {
        String email = EditTextEmail.getText().toString().trim();
        String Password = EditTextPassword.getText().toString().trim();
        String Name = EditTextName.getText().toString().trim();
        String Age = EditTextAge.getText().toString().trim();

        if(Name.isEmpty()){
            EditTextName.setError("Full Name is Required");
            EditTextName.requestFocus();
            return;
        }
        if(Age.isEmpty()){
            EditTextAge.setError("Age is Required");
            EditTextAge.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            EditTextEmail.setError("Please provide valid email");
            EditTextEmail.requestFocus();
            return;
        }

        if(Password.isEmpty()){
            EditTextPassword.setError("Password is Required");
            EditTextPassword.requestFocus();
            return;
        }
        if(Password.length() < 6){
            EditTextPassword.setError("Password should be more than 6 Characters");
            EditTextPassword.requestFocus();
            return;
        }


    }
}