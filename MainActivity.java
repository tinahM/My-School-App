package com.wagura.studentsmodule;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.sql.Time;

public class MainActivity extends AppCompatActivity {
    private Button Profile;
    private Button Fees;
    private Button Attendance;
    private Button Coursework_marks;
    private Button TimeTable;
    private Button Logout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Profile = findViewById(R.id.Profile);
        Profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openProfile();
            }

        });
        Fees = findViewById(R.id.Fees);
        Fees.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFees();
            }

        });
        Attendance = findViewById(R.id.Attendance);
        Attendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAttendance();
            }

        });
        Coursework_marks = findViewById(R.id.Coursework);
        Coursework_marks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCoursework_marks();
            }

        });
        TimeTable = findViewById(R.id.TimeTable);
        TimeTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTimeTable();
            }

        });
        Logout = findViewById(R.id.Logout);
        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLogout();
            }

        });

    }

    public void openFees() {
        Intent intent = new Intent(this, Fees.class);
        startActivity(intent);
    }


    public void openProfile() {
        Intent intent = new Intent(this, Profile.class);
        startActivity(intent);
    }
    public void openAttendance() {
        Intent intent = new Intent(this, Attendance.class);
        startActivity(intent);
    }
    public void openCoursework_marks() {
        Intent intent = new Intent(this, CourseworkMarks.class);
        startActivity(intent);
    }
    public void openTimeTable() {
        Intent intent = new Intent(this, TimeTable.class);
        startActivity(intent);
    }
    public void openLogout() {
        Intent intent = new Intent(this, Logout.class);
        startActivity(intent);
    }



}
