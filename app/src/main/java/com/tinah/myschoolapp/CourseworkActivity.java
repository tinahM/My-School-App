package com.tinah.myschoolapp;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.tinah.myschoolapp.R;
import com.tinah.myschoolapp.AppBase;
import com.tinah.myschoolapp.databaseHandler;

public class CourseworkActivity extends AppCompatActivity {
    EditText c1, c2, c3, c4, c5;
    Button b3;
    float sg1, sg2, sg3, sg4, sg5,  cg;
    databaseHandler handler = AppBase.handler;
    Activity profileActivity = this;
    ListView listView;
    ProfileAdapter adapter;
    ArrayList<String> dates;
    ArrayList<String> datesALONE;
    ArrayList<Integer> hourALONE;
    ArrayList<Boolean> atts;
    Activity activity = this;
    private View v;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stu_profile2);
        dates = new ArrayList<>();
        datesALONE = new ArrayList<>();
        hourALONE = new ArrayList<>();
        atts = new ArrayList<>();

        listView = (ListView) findViewById(R.id.attendProfileView_list);


        TextView textView = (TextView) findViewById(R.id.profileContentView);
        assert textView != null;

        Button findButton = (Button) findViewById(R.id.buttonFind);
        assert findButton != null;
        findButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                find(v);
                course(v);
            }
        });






    }
    public void course(View view){
        EditText editText = (EditText) findViewById(R.id.editText);
        final String reg = editText.getText().toString();
        setContentView(R.layout.activity_cgpa);
        c1 = findViewById(R.id.c1);
        c2 = findViewById(R.id.c2);
        c3 = findViewById(R.id.c3);
        c4 = findViewById(R.id.c4);
        c5 = findViewById(R.id.c5);
        b3 = findViewById(R.id.b3);

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sg1 = read(c1);
                sg2 = read(c2);
                sg3 = read(c3);
                sg4 = read(c4);
                sg5 = read(c5);

                cg = (((20 * sg1) / sg2) + ((20 * sg3) / sg4) + ((6 * sg5) / 10));

                Intent i3 = new Intent(getApplicationContext(), ResultActivity.class);
                i3.putExtra("final_sgpa", cg);
                i3.putExtra("flag", 0);
                i3.putExtra("final_perc", 0);


                try {
                    String qu = "INSERT INTO STUDENT  WHERE regno = '" + reg.toUpperCase() + "' = '" + cg + "'";

                    final Cursor cursor = handler.execQuery(qu);
                }catch (Exception e)
                {
                    Toast.makeText(activity,"Error Occured for Inserting Student Marks",Toast.LENGTH_LONG).show();
                }
                startActivity(i3);

            }

        });
    }

    public void find(View view) {
        // setContentView(R.layout.activity_coursework);
        dates.clear();
        atts.clear();
        final EditText editText = (EditText) findViewById(R.id.editText);
        final TextView textView = (TextView) findViewById(R.id.profileContentView);
        final String reg = editText.getText().toString();
        String qu = "SELECT * FROM STUDENT WHERE regno = '" + reg.toUpperCase() + "'";
        String qc = "SELECT * FROM ATTENDANCE WHERE register = '" + reg.toUpperCase() + "';";
        String qd = "SELECT * FROM ATTENDANCE WHERE register = '" + reg.toUpperCase() + "' AND isPresent = 1";
        Cursor cursor = handler.execQuery(qu);
        float att = 0f;
        if (cursor == null || cursor.getCount() == 0) {
            assert textView != null;
            textView.setText("No Data Available");
        } else {
            String attendance = "";
            if (att < 0) {
                attendance = "Attendance Not Available";
            } else
                attendance = " Attendance " + att + " %";
            cursor.moveToFirst();
            String buffer = "";
            buffer += " " + cursor.getString(0) + "\n";
            buffer += " " + cursor.getString(1) + "\n";
            buffer += " " + cursor.getString(2) + "\n";
            buffer += " " + cursor.getString(3) + "\n";
            buffer += " " + cursor.getInt(4) + "\n";
            buffer += " " + attendance + "\n";
            textView.setText(buffer);

        }

    }

    private float read(EditText c) {
        if (c.getText().toString().matches("")) {
            return 0;

        } else {
            return (Float.valueOf(c.getText().toString()).floatValue());
        }
    }
}

