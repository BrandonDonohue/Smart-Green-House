package com.example.bmeister.smartgreenapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import static com.example.bmeister.smartgreenapp.R.id.btnenvironment;
import static com.example.bmeister.smartgreenapp.R.id.btnlogin;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //go to environment activity
        Button environment = (Button)findViewById(R.id.btnenvironment);
        environment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), EnvironmentActivity.class);
                startActivity(intent);
            }
        });

        //go to user settings activity
        Button settings = (Button)findViewById(R.id.btnusersettings);
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), UserSettingsActivity.class);
                startActivity(intent);
            }
        });

        //go to help activity
        Button help = (Button)findViewById(R.id.btnhelp);
        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), HelpActivity.class);
                startActivity(intent);
            }
        });

        //log out
        Button logout = (Button)findViewById(R.id.btnlogout);
        logout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Runnable clickButton = new Runnable(){
                    @Override
                    public void run(){
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    }
                };
                logout.postDelayed(clickButton, 2000);

            }
        });
    }

}
