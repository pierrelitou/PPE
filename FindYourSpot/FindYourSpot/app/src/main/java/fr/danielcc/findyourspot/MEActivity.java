package fr.danielcc.findyourspot;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Button;
import android.view.View;

import android.content.Intent;
import java.util.ArrayList;
import java.util.HashMap;

public class MEActivity extends AppCompatActivity{

    //JSON TAGs
    public static final String TAG_JSON_ARRAY="result";
    public static final String TAG_firstname = "firstname";
    public static final String TAG_lastname = "lastname";
    public static final String TAG_dateofbirth = "dateofbirth";
    public static final String TAG_pseudo = "pseudo";

    private ListView listView;

    private Button MAP;
    private Button ACTIVITIES;
    private Button EVENT;
    private Button ME;
    private Button logout;

    private ImageView photo;
    SharedPreferences sharedpreferences;

    public static final String TAG_ID = "id";
    public static final String TAG_USERNAME = "username";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_me);

        listView = (ListView) findViewById(R.id.listView);
        photo = (ImageView) findViewById(R.id.photoprofil);

        String pack = getPackageName();
        pack = pack + ":drawable/";
        pack = pack + Server.photoprofil;
        int id = getResources().getIdentifier(pack,null,null);
        photo.setImageResource(id);

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
                Intent otherActivity = new Intent(getApplicationContext(), Activities.class);
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
                Server.photoprofil="";
                editor.commit();
                startActivity(otherActivity);
                finish();
            }
        });

        DisplayData();
    }

    private void DisplayData(){

        ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String, String>>();

                HashMap<String,String> data_profil = new HashMap<>();

                data_profil.put(TAG_firstname,Server.firstname);
                data_profil.put(TAG_lastname,Server.lastname);
                data_profil.put(TAG_dateofbirth,Server.dateofbirth);
                data_profil.put(TAG_pseudo,Server.pseudo);

                list.add(data_profil);




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

