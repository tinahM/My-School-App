package com.wagura.studentsmodule;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Attendance extends AppCompatActivity {
    private Button button1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);
        button1= findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAttendanceDetails();
            }

        });


    }

    private void openAttendanceDetails() {
        Intent intent = new Intent(this, AttendanceDetails.class);
        startActivity(intent);
    }
    }

