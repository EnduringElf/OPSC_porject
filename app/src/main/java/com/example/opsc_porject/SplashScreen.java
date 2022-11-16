package com.example.opsc_porject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SplashScreen extends AppCompatActivity {

    Button switchToLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        switchToLogin = findViewById(R.id.button);
        switchToLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                SwitchToLogin();
            }
        });
    }

    private void SwitchToLogin(){
        Intent switchToLoginIntent = new Intent(this, SignInActivity.class);
        startActivity(switchToLoginIntent);
    }
}