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

    TextView firstNameView;
    TextView lastNameView;
    TextView markView;

    DBHelper dbconnect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup_window);

        firstNameView = (TextView) findViewById(R.id.firstName);
        lastNameView =  (TextView) findViewById(R.id.lastName);
        markView =      (TextView) findViewById(R.id.mark);

        Button btnSave = (Button) findViewById(R.id.save);
        //Button btnCancel = (Button) findViewById(R.id.cancel);

        // Screen Dimension
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int height = dm.heightPixels;
        int width = dm.widthPixels;

        getWindow().setLayout((int) (width*.8), (int) (height*.5));


        // Binding Click event to Update Button
        btnSave.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                // Insert/Update record in the database
                execute_database();

                //Closing CurrentScreen Activity
                finish();
            }
        });

    }

    public void execute_database()
    {

        // Getting Activity form data for DataBase update/insert tasks
        Bundle extras = getIntent().getExtras();
        int     id   = extras.getInt("id");

        String fname = firstNameView.getText().toString();
        String lname = lastNameView.getText().toString();
        String mark = markView.getText().toString();


        // Database
        dbconnect = new DBHelper(this);

        if(extras !=null)
        {
            if(id > 0){
                if(dbconnect.updateStudent(id,fname,lname,mark)){
                    Toast.makeText(getApplicationContext(), "Updated", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getApplicationContext(), "not Updated", Toast.LENGTH_SHORT).show();
                }
            }
            else{
                if(dbconnect.insertStudent(fname, lname, mark)){
                    Toast.makeText(getApplicationContext(), "Student Inserted", Toast.LENGTH_SHORT).show();
                }

                else{
                    Toast.makeText(getApplicationContext(), "not done", Toast.LENGTH_SHORT).show();
                }

            }
        }

    }
}