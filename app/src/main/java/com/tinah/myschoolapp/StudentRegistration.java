package com.tinah.myschoolapp;


import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.tinah.myschoolapp.R;
import com.tinah.myschoolapp.AppBase;

public class StudentRegistration extends AppCompatActivity {


    Activity activity = this;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student__registartion);

        spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, AppBase.divisions);
        spinner.setAdapter(adapter);

        Button btn = (Button) findViewById(R.id.buttonSAVE);
        assert btn != null;
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveToDatabase(v);
            }
        });
    }


    public void saveToDatabase(View view) {
        EditText name = (EditText) findViewById(R.id.edit_name);
        EditText roll = (EditText) findViewById(R.id.roll);
        EditText register = (EditText) findViewById(R.id.register);
        EditText contact = (EditText) findViewById(R.id.contact);
        String classSelected = spinner.getSelectedItem().toString();
        int coursework = 0;

        if (name.getText().length() < 2 || roll.getText().length() == 0 || register.getText().length() < 2 ||
                contact.getText().length() < 2 || classSelected.length() < 2){
            AlertDialog.Builder alert = new AlertDialog.Builder(activity);
            alert.setTitle("Invalid");
            alert.setMessage("Insufficient Data");
            alert.setPositiveButton("OK", null);
            alert.show();
            return;
        }

        String qu = "INSERT INTO STUDENT VALUES('" + name.getText().toString() + "'," +
                "'" + classSelected + "'," +
                "'" + register.getText().toString().toUpperCase() + "'," +
                "'" + contact.getText().toString() + "'," +
                "" + Integer.parseInt(roll.getText().toString()) + ");";
        String qb = "INSERT INTO COURSEWORK VALUES('" + name.getText().toString() + "',"+"'"+coursework+"')";
        Log.d("Student Reg", qu);
        AppBase.handler.execAction(qu);
        AppBase.handler.execAction(qb);
        qu = "SELECT * FROM STUDENT WHERE regno = '" + register.getText().toString() + "';";
        Log.d("Student Reg", qu);
        Log.d("Student Reg", qb);
        if (AppBase.handler.execQuery(qu) != null) {
            Toast.makeText(getBaseContext(), "Student Added", Toast.LENGTH_LONG).show();
            this.finish();
        }
    }
}
