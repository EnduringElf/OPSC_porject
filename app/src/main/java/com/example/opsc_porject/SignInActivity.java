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
import com.google.firebase.auth.FirebaseUser;

public class SignInActivity extends AppCompatActivity  implements View.OnClickListener{

    private TextView RegisterButton;
    private TextView Forgotpassword;
    private EditText email, password;
    private Button Login;
    private FirebaseAuth mAuth;
    private ProgressBar progressBar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        RegisterButton = (TextView) findViewById(R.id.RegisterTV);
        RegisterButton.setOnClickListener(this);
        Forgotpassword = (TextView) findViewById(R.id.ForgotPasswordTV);
        Forgotpassword.setOnClickListener(this);

        Login = (Button) findViewById(R.id.LoginBTN);
        Login.setOnClickListener(this);

        email = (EditText) findViewById(R.id.EmailAddressTV);
        password = (EditText) findViewById(R.id.PasswordTV);
        progressBar = (ProgressBar) findViewById(R.id.ProgressBarSignin);
        mAuth = FirebaseAuth.getInstance();


    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.RegisterTV:
                startActivity(new Intent(this, RegistrationActivity.class));
                break;
                case R.id.LoginBTN:
                    userLogin(this);
                    break;
            case R.id.ForgotPasswordTV:
                startActivity(new Intent(this, Map_activity.class));
                break;
        }
    }

    private void userLogin(Context c) {
        String Email = email.getText().toString().trim();
        String Password = password.getText().toString().trim();

        if(Email.isEmpty()){
            email.setError("Email is required");
            email.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(Email).matches()){
            email.setError("Please enter a valid email!");
            email.requestFocus();
            return;
        }
        if(Password.isEmpty()){
            password.setError("Password is required");
            password.requestFocus();
            return;
        }
        if(password.length() < 6){
            password.setError("min password length is 6");
            password.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        mAuth.signInWithEmailAndPassword(Email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    //redirect to app
                    startActivity(new Intent(c, Map_activity.class));
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(SignInActivity.this, "User has successfully Logged in", Toast.LENGTH_LONG).show();

                }else{
                    Toast.makeText(SignInActivity.this, "Failed to login! Please check your credentials", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}