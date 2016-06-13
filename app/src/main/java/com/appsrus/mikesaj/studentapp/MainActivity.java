package com.appsrus.mikesaj.studentapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.view.View;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import com.appsrus.mikesaj.studentapp.DAL.DBHelper;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    // Declaring the ListView, ImageButton, List collection and Database_Helper objects for this activity
    ListView studentListView = null;
    ImageButton addButton;
    List<StudentsInfo> studentinfoList;
    List<String> studentList;
    DBHelper dbconnect;
    String studentName;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;


    // MainActivity Constructor
    public MainActivity() {
        // Instantiating Student information list object
        studentinfoList = new ArrayList<StudentsInfo>();

        // Initialize database helper class object
        dbconnect = new DBHelper(this);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialising our student listView
        studentListView = (ListView) findViewById(R.id.listView1);

        // Cast and instantiate add student button
        addButton = (ImageButton) findViewById(R.id.add_button);

        // Setting an onClickListener to Add_Student data
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){

                Intent AddStudentActivity = new Intent(MainActivity.this, Popup.class);

                //Posting data to another Activity
                AddStudentActivity.putExtra("id", 0);

                // Start Activity
                startActivityForResult(AddStudentActivity, 100);
            }

        });

        // This method call populates the listView from the database
        this.populate_listView();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    // Function to read the result from newly created activity
    @Override
    protected void onActivityResult(int requestCode,
                                    int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        this.populate_listView();
    }

    @Override
    public void onItemClick(AdapterView<?> arg0, View view, int pos, long id) {

        // Locate selected Student with position-number
        StudentsInfo studentData = getStudentData(pos);

        String selectedStudentData = studentData.firstName+" "+ studentData.lastName;

        // Display toast message of selected student
        Toast.makeText(getApplicationContext(), "Student: " + selectedStudentData, Toast.LENGTH_LONG).show();

        //Starting the ViewStudentActivity Screen and posting  student ID, Name & Mark
        Intent viewStudentActivity = new Intent(getApplicationContext(), Viewstudent.class);

        //Posting data to another Activity
        viewStudentActivity.putExtra("id",   studentData.Id);
        viewStudentActivity.putExtra("firstName", studentData.firstName);
        viewStudentActivity.putExtra("lastName", studentData.lastName);
        viewStudentActivity.putExtra("mark", studentData.Mark);

        // start the ViewStudent activity
        startActivityForResult(viewStudentActivity, 100);
    }

    /*
     * @return Student data from List with a position condition
     */
    private StudentsInfo getStudentData(int position){

        //Get StudentsInfo data from StudentList at a particular position
        return studentinfoList.get(position);
    }

    private void populate_listView(){


        // Building the list of students that we will display in our
        // ListView
        studentList = new ArrayList<String>();

        // Initialize the table with student info
        studentinfoList = dbconnect.getAllStudent();

        // Convert the list to adapterlist for the listView
        for (StudentsInfo studentdata : studentinfoList) {

            // concatenate firstName and lastName
            studentName = studentdata.firstName + " " + studentdata.lastName;

            // Add student to Students list
            studentList.add(studentName);
        }

        // If database is empty of student records
        if(studentinfoList.isEmpty()){

            // Add Error message of an empty list
            studentList.add(" Error!\nNo Student Data Found");
            studentListView.setOnItemClickListener(null);
        }


        // Instatiating an listadapter object
        ListAdapter adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, studentList);

        // Populate student data to our listview
        studentListView.setAdapter(adapter);

        // Check if studentData list is empty
        if(!studentinfoList.isEmpty()){

            // Add click listener to each record on the student Listview
            studentListView.setOnItemClickListener(this);
        }
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.appsrus.mikesaj.studentapp/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.appsrus.mikesaj.studentapp/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}

