package fr.danielcc.myapplication3;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Button;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.content.Intent;
import java.util.ArrayList;
import java.util.HashMap;

public class MEActivity extends AppCompatActivity{
    //Link to database


    //JSON Tagsvity
    public static final String TAG_JSON_ARRAY="result";
    public static final String TAG_firstname = "firstname";
    public static final String TAG_lastname = "lastname";
    public static final String TAG_dateofbirth = "dateofbirth";
    public static final String TAG_pseudo = "pseudo";
    //public static final String TAG_IMG = "lienimg";


    //public ImageView photoprofil;

    private ListView listView;
    private String JSON_STRING;

    private Button MAP;
    private Button ACTIVITIES;
    private Button EVENT;
    private Button ME;
    private Button logout;

    SharedPreferences sharedpreferences;

    public static final String TAG_ID = "id";
    public static final String TAG_USERNAME = "username";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_me);

        listView = (ListView) findViewById(R.id.listView);

        this.MAP = (Button) findViewById(R.id.CARTEME);
        this.ACTIVITIES = (Button) findViewById(R.id.ACTIVITESME);
        this.EVENT = (Button) findViewById(R.id.EVENEMENTME);
        this.ME = (Button) findViewById(R.id.MEME);
        this.logout = (Button) findViewById(R.id.logout);

        MAP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent otherActivity = new Intent(getApplicationContext(), MapsActivity.class);
                startActivity(otherActivity);
                finish();
            }
        });

        EVENT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent otherActivity = new Intent(getApplicationContext(), EventActivity.class);
                startActivity(otherActivity);
                finish();
            }
        });

        ACTIVITIES.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent otherActivity = new Intent(getApplicationContext(), MainActivity2.class);
                startActivity(otherActivity);
                finish();
            }
        });

        ME.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent otherActivity = new Intent(getApplicationContext(), MEActivity.class);
                startActivity(otherActivity);
                finish();
            }
        });

        sharedpreferences = getSharedPreferences(Login.my_shared_preferences, Context.MODE_PRIVATE);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent otherActivity = new Intent(getApplicationContext(), Login.class);
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putBoolean(Login.session_status, false);
                editor.putString(TAG_ID, null);
                editor.putString(TAG_USERNAME, null);
                Server.pseudo="";
                Server.dateofbirth="";
                Server.lastname="";
                Server.firstname="";
                editor.commit();
                startActivity(otherActivity);
                finish();
            }
        });

        showEmployee();
    }

    private void showEmployee(){

        ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String, String>>();

                HashMap<String,String> employees = new HashMap<>();

                employees.put(TAG_firstname,Server.firstname);
                employees.put(TAG_lastname,Server.lastname);
                employees.put(TAG_dateofbirth,Server.dateofbirth);
                employees.put(TAG_pseudo,Server.pseudo);

                list.add(employees);




        ListAdapter adapter = new SimpleAdapter(
                MEActivity.this, list, R.layout.list_item_me,
                new String[]{
                        TAG_firstname,
                        TAG_lastname,
                        TAG_dateofbirth,
                        TAG_pseudo,
                },
                new int[]{
                        R.id.firstname,
                        R.id.lastname,
                        R.id.dateofbirth,
                        R.id.pseudo,
                });

        listView.setAdapter(adapter);
    }


}

