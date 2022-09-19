package com.example.opsc_porject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SignInActivity extends AppCompatActivity  implements View.OnClickListener{

    private TextView RegisterButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        RegisterButton = (TextView) findViewById(R.id.RegisterTV);
        RegisterButton.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.RegisterTV:
                startActivity(new Intent(this, RegistrationActivity.class));
                break;
        }
    }
}