package fr.danielcc.myapplication3;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class EventActivity extends AppCompatActivity {

    //Link to database
    public static final String URL_GET_ALL = "http://192.168.43.192/findyourspot/GetActivities.php";

    //JSON Tagsvity
    public static final String TAG_JSON_ARRAY="result";
    public static final String TAG_ID = "id";
    public static final String TAG_NAME = "nameactivity";
    public static final String TAG_LOC = "location";
    public static final String TAG_DES = "description";
    //public static final String TAG_IMG = "lienimg";

    private ListView listView;
    private String JSON_STRING;

    private Button MAP;
    private Button ACTIVITIES;
    private Button EVENT;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        listView = (ListView) findViewById(R.id.listView);

        //listView.setOnItemClickListener(this);
        getJSON();

        this.MAP = (Button) findViewById(R.id.CARTEEVENT);
        this.ACTIVITIES = (Button) findViewById(R.id.ACTIVITESEVENT);
        this.EVENT = (Button) findViewById(R.id.EVENEMENTEVENT);

        MAP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent otherActivity = new Intent(getApplicationContext(), MapsActivity.class);
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
    }

}
