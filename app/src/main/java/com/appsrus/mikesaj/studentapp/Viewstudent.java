package com.appsrus.mikesaj.studentapp;

import android.app.Activity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.appsrus.mikesaj.studentapp.DAL.DBHelper;


/**
 * Created by MikeSaj on 10/06/2016.
 */
public class Viewstudent extends Activity{

    // Declaring string variables to store student info
    String student_Id, firstName_txt, lastName_txt, mark;

    // Defining textView objects by ID names
    TextView studentId, firstName, lastName, studentMark;

    // Defining Activity Button object
    Button btnUpdate, btndelete;
    DBHelper dbconnect;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get the activity to display
        setContentView(R.layout.viewstudent);

        // This method initializes UI objects of this activity
        this.uiObjects();

        // Initializes our database helper class
        dbconnect = new DBHelper(this);

        // Receiving data posted from parent Activity
        this.getPosteddata();

        // Binding Click event to Update Button
        btnUpdate.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {

            Intent AddStudentActivity = new Intent(Viewstudent.this, Popup.class);

            //Posting data to another Activity
            AddStudentActivity.putExtra("firstName", firstName_txt);
            AddStudentActivity.putExtra("id", student_Id);
            AddStudentActivity.putExtra("lastName", lastName_txt);
            AddStudentActivity.putExtra("mark", mark);

            // Start popup Activity
            startActivity(AddStudentActivity);

            // Post status code to calling 'Popup' Activity
            refreshPriorActivity();

            //Closing CurrentScreen Activity
            finish();
            }
        });

        // Binding Click event to Update Button
        btndelete.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                //Closing CurrentScreen Activity
            int id = Integer.parseInt(student_Id);
            dbconnect.deleteStudent(id);

            // Send status code to calling Activity
            refreshPriorActivity();

            // Display a toast message after deleting a student record
            Toast.makeText(getApplicationContext(), firstName_txt+" "+lastName_txt+" Deleted", Toast.LENGTH_SHORT).show();

            finish();
            }
        });

    }

    // This method defines and initializes the UI objects and components
    private void uiObjects(){

        // Defining textView objects by ID names
        studentId = (TextView) findViewById(R.id.studentId);
        firstName = (TextView) findViewById(R.id.firstName);
        lastName = (TextView) findViewById(R.id.lastName);
        studentMark = (TextView) findViewById(R.id.mark);

        // Initialize button objects with activity object ids
        btnUpdate = (Button) findViewById(R.id.update);
        btndelete = (Button) findViewById(R.id.delete);

    }
    // Receiving data posted from parent Activity
    private void getPosteddata(){

        Intent i = getIntent();

        // Getting posted data by id
        student_Id    = i.getStringExtra("id");
        firstName_txt = i.getStringExtra("firstName");
        lastName_txt  = i.getStringExtra("lastName");
        mark          = i.getStringExtra("mark");

        // Setting and displaying Received data on Activity objects
        studentId.setText(student_Id);
        firstName.setText(firstName_txt);
        lastName.setText(lastName_txt);
        studentMark.setText(mark);

    }
    // This method returns data to the parent Activity
    public void refreshPriorActivity(){

        Intent i = new Intent();
    // Sending param key as 'status' and data as 'updated'
        i.putExtra("status", "updated");

    // Setting resultCode to 100 to identify on old activity
        setResult(100,i);
    }
}
