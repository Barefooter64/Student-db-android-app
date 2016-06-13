package com.appsrus.mikesaj.studentapp;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.appsrus.mikesaj.studentapp.DAL.DBHelper;

/**
 * Created by MikeSaj on 10/06/2016.
 */
public class Popup extends Activity {

    // Declaring the TextView objects for this activity
    TextView add_studentView;
    TextView firstNameView;
    TextView lastNameView;
    TextView markView;
    Button btnSave, btnCancel;

    // Declaring string variables to store student info
    String fname, lname , mark;

    Bundle extras;
    DBHelper dbconnect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup_window);

        this.objInit();

        // get Screen Dimension from DisplayMetrics class
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int height = dm.heightPixels;
        int width = dm.widthPixels;

        // Set activity screen size when viewed as an overlay
        getWindow().setLayout((int) (width*.8), (int) (height*.6));

        // Check if its to update DB record
        extras = getIntent().getExtras();
        updateStudent();

        // Binding Click event to Update Button
        btnSave.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                // Insert/Update record in the database
                execute_database();

                //Closing CurrentScreen Activity
                finish();
            }
        });

        // Binding Click event to Cancel Button
        btnCancel.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                //Closing CurrentScreen Activity
                finish();
            }
        });

    }

    // This method prepares the UI objects in this activity for an update operation
    // by setting the TextView objects with Data
    public void updateStudent(){

        int id = 0;
        try {
            id = Integer.parseInt(extras.getString("id"));
        }
        catch(Exception e){}

        if(id > 0){
            fname = extras.getString("firstName");
            lname = extras.getString("lastName");
            mark = extras.getString("mark");

            // Set Activity data to be updated
            add_studentView.setText("Edit Student");
            firstNameView.setText(fname);
            lastNameView.setText(lname);
            markView.setText(mark);
            btnSave.setText("Update");
        }

    }

    // This method performs insert/update tasks on the database
    public void execute_database()
    {
        // Getting Activity form data for DataBase update/insert tasks
        int id = 0;
        try {
            id = Integer.parseInt(extras.getString("id"));
        }
        catch (Exception e){}


        // Collecting Student data as a parameter for database  operations
        String fname = firstNameView.getText().toString();
        String lname = lastNameView.getText().toString();
        String mark = markView.getText().toString();


        // Database help object instantiation
        dbconnect = new DBHelper(this);

        // Checks if its an Insert or Update operation
        if(extras !=null)
        {
            if(id > 0){
                // Attempt an update operation
                if(dbconnect.updateStudent(id,fname,lname,mark)){
                    // Display a toast if update is successful
                    Toast.makeText(getApplicationContext(), "Updated", Toast.LENGTH_SHORT).show();
                }
                else{// Display an error message toast if update is unsuccessful
                    Toast.makeText(getApplicationContext(), "not Updated", Toast.LENGTH_SHORT).show();
                }
            }
            else{// Attempt an insert operation
                if(dbconnect.insertStudent(fname, lname, mark)){
                    // Display a toast message if insert is successful
                    Toast.makeText(getApplicationContext(), "Student Inserted", Toast.LENGTH_SHORT).show();
                }

                else{// Display an error message toast if insert is unsuccessful
                    Toast.makeText(getApplicationContext(), "not done", Toast.LENGTH_SHORT).show();
                }

            }
        }

    }


    // This method initializes objects of this Activity
    private void objInit(){
        add_studentView = (TextView) findViewById(R.id.add_student);
        firstNameView = (TextView) findViewById(R.id.firstName);
        lastNameView =  (TextView) findViewById(R.id.lastName);
        markView =      (TextView) findViewById(R.id.mark);

        btnSave = (Button) findViewById(R.id.save);
        btnCancel = (Button) findViewById(R.id.cancel);
    }

}