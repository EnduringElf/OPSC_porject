package com.example.opsc_porject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

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
                registerUser(this);
                break;
        }

    }

    private void registerUser(Context PackageContent) {
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

        progressBar.setVisibility(View.VISIBLE);

        mAuth.createUserWithEmailAndPassword(email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    User user = new User(Name,Age,email);

                    FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(RegistrationActivity.this, "User has been registered successfully", Toast.LENGTH_LONG).show();
                                progressBar.setVisibility(View.GONE);

                                //redirect to rest of app
                                startActivity(new Intent(PackageContent, SignInActivity.class));


                            }else {
                                Toast.makeText(RegistrationActivity.this, "Failed to register user! Please try again!", Toast.LENGTH_LONG).show();
                                progressBar.setVisibility(View.INVISIBLE);
                            }
                        }
                    });


                }else {
                    Toast.makeText(RegistrationActivity.this, "Failed to register", Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.INVISIBLE);
                }
            }
        });


    }
}