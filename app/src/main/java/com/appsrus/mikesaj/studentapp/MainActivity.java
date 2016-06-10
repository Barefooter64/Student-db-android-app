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

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    // Declare our listview
    ListView studentListView = null;
    ImageButton addButton;
    List<StudentsInfo> studentinfoList;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;


    // MainActivity Constructor
    public MainActivity() {
        // Instantiating Student information list object
        studentinfoList = new ArrayList<StudentsInfo>();
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
                startActivity(new Intent(MainActivity.this, Popup.class));
            }

        });

        // Building the list of students that we will display in our
        // ListView
        List<String> studentList = new ArrayList<String>();


        // Student info
        StudentsInfo StudentInfo = new StudentsInfo();
        // Initialize the table with student
        StudentInfo.Id = "76543";
        StudentInfo.Name = "Mike123";

        studentinfoList.add(StudentInfo);
        studentinfoList.add(StudentInfo);
        studentinfoList.add(StudentInfo);
        studentinfoList.add(StudentInfo);
        studentinfoList.add(StudentInfo);
        studentinfoList.add(StudentInfo);
        studentinfoList.add(StudentInfo);
        studentinfoList.add(StudentInfo);
        studentinfoList.add(StudentInfo);
        studentinfoList.add(StudentInfo);
        studentinfoList.add(StudentInfo);
        studentinfoList.add(StudentInfo);
        studentinfoList.add(StudentInfo);
        studentinfoList.add(StudentInfo);
        studentinfoList.add(StudentInfo);
        studentinfoList.add(StudentInfo);
        studentinfoList.add(StudentInfo);
        studentinfoList.add(StudentInfo);


        // Convert the list to adapterlist for the table
        for (StudentsInfo student : studentinfoList) {

            // Add student to Students list
            studentList.add(student.Name);
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

        Toast.makeText(getApplicationContext(),
                "Student Position: " + pos, Toast.LENGTH_LONG).show();

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

class StudentsInfo{
    String Id;
    String Name;
}