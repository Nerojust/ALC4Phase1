package com.example.alc4phase1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ActivityOne extends AppCompatActivity {

    private Button goToAboutALCactivity;
    private Button goToMyProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeViews();
        initializeListeners();
    }

    private void initializeViews() {
        goToAboutALCactivity = findViewById(R.id.about_alc);
        goToMyProfile = findViewById(R.id.myProfile);
    }

    private void initializeListeners() {
        goToAboutALCactivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToALCactivity();
            }
        });
        goToMyProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToMyProfileActivity();
            }
        });
    }

    private void goToALCactivity() {
        Intent intent = new Intent(ActivityOne.this, ActivityTwo.class);
        startActivity(intent);
    }

    private void goToMyProfileActivity() {
        Intent intent = new Intent(ActivityOne.this, ActivityThree.class);
        startActivity(intent);
    }
}
