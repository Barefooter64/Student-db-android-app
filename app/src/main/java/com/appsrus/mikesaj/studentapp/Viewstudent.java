package com.appsrus.mikesaj.studentapp;

import android.app.Activity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


/**
 * Created by MikeSaj on 10/06/2016.
 */
public class Viewstudent extends Activity{


    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get the activity to display
        setContentView(R.layout.viewstudent);

        TextView studentId = (TextView) findViewById(R.id.studentId);
        TextView studentName = (TextView) findViewById(R.id.studentName);
        TextView studentMark = (TextView) findViewById(R.id.mark);
        Button btnUpdate = (Button) findViewById(R.id.update);
        Button deleteUpdate = (Button) findViewById(R.id.delete);

        Intent i = getIntent();

        // Receiving the Data
        String id   = i.getStringExtra("id");
        String name = i.getStringExtra("name");
        String mark = i.getStringExtra("mark");

        Log.e("Second Screen data ", name + "." + mark);

        // Setting and displaying Received data on Activity objects
        studentId.setText(id);
        studentName.setText(name);
        studentMark.setText(mark);

        // Binding Click event to Update Button
        btnUpdate.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {

                Intent AddStudentActivity = new Intent(Viewstudent.this, Popup.class);

                //Posting data to another Activity
                AddStudentActivity.putExtra("id", 1);

                // Start Activity
                startActivity(AddStudentActivity);

                //Closing CurrentScreen Activity
                finish();
            }
        });

        // Binding Click event to Update Button
        deleteUpdate.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                //Closing CurrentScreen Activity
                finish();
            }
        });

    }


}
