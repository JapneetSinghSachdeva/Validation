package com.alphaindelhi.signupconstrained;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button Login, signUP;

        Login = findViewById(R.id.Login);
        signUP = findViewById(R.id.signUP);

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent Login = new Intent(MainActivity.this , Login.class);
                startActivity(Login);

            }
        });

        signUP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent SignUp = new Intent(MainActivity.this , SignUp.class);
                startActivity(SignUp);
            }
        });

    }


}
