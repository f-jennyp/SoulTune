package com.example.music;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class About_page extends AppCompatActivity {
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_page);
        button=(Button) findViewById(R.id.meet_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMeetTheTeam();
            }

            public void openMeetTheTeam(){
                Intent intent= new Intent(About_page.this, Meet_the_team.class);
                startActivity(intent);
            }
        });
    }
}