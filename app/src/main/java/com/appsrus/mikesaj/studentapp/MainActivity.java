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

    // Declare our listview
    ListView studentListView = null;
    ImageButton addButton;
    List<StudentsInfo> studentinfoList;
    DBHelper dbconnect;

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

        // recuperation de notre composant graphique
        studentListView = (ListView) findViewById(R.id.listView1);

        // Cast and instantiate add student button
        addButton = (ImageButton) findViewById(R.id.add_button);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){

                Intent AddStudentActivity = new Intent(MainActivity.this, Popup.class);

                //Posting data to another Activity
                AddStudentActivity.putExtra("id", 0);

                // Start Activity
                startActivity(AddStudentActivity);
            }

        });

        // Building the list of students that we will display in our
        // ListView
        List<String> studentList = new ArrayList<String>();


        // Student info
        StudentsInfo Student = new StudentsInfo();
        // Initialize the table with student
        Student.Id = 76543;
        Student.Name = "Mike123";
        Student.Mark = "100%";

        studentinfoList.add(Student);

        StudentsInfo Student1 = new StudentsInfo();

        Student1.Id = 222;
        Student1.Name = "Ebahi";
        Student1.Mark = "75%";

        studentinfoList.add(Student1);


        //////////// FROM Database



        // Convert the list to adapterlist for the table
        for (StudentsInfo studentdata : studentinfoList) {


            System.out.println(studentdata.Name);

            // Add student to Students list
            studentList.add(studentdata.Name);
        }

        // Initialize adapter
        ListAdapter adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, studentList);

        // set adapter to our listview
        studentListView.setAdapter(adapter);

        // Add click listener to each record on the student Listview
        studentListView.setOnItemClickListener(this);

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public void onItemClick(AdapterView<?> arg0, View view, int pos, long id) {
        //

        //Toast.makeText(getApplicationContext(),
        //        "Student Position: " + pos, Toast.LENGTH_LONG).show();

        StudentsInfo studentData = getStudentData(pos);

        String selectedStudentData = studentData.Name+" "+ studentData.Mark;

                Toast.makeText(getApplicationContext(),
                "Student Position: " + selectedStudentData, Toast.LENGTH_LONG).show();




        //Starting the ViewStudentActivity Screen and posting  student ID, Name & Mark
        Intent viewStudentActivity = new Intent(getApplicationContext(), Viewstudent.class);

        //Posting data to another Activity
        viewStudentActivity.putExtra("id",   studentData.Id);
        viewStudentActivity.putExtra("name", studentData.Name);
        viewStudentActivity.putExtra("mark", studentData.Mark);

        //startActivity(new Intent(getApplicationContext(), Popup.class));
        startActivity(viewStudentActivity);

    }

    /*
     * @return Student data from List with a position condition
     */
    private StudentsInfo getStudentData(int position){

        //Get StudentsInfo data from StudentList at a particular position
        StudentsInfo studentData = studentinfoList.get(position);
        return studentData;
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

