package fr.danielcc.myapplication3;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
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


public class EventActivity extends AppCompatActivity {

    //Link to database
    public static final String URL_GET_ALL = "http://192.168.43.146/findyourspot/GetEvent.php";

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
    private Button ME;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        listView = (ListView) findViewById(R.id.listView);

        //listView.setOnItemClickListener(this);
        getJSON();

        this.MAP = (Button) findViewById(R.id.CARTEEV);
        this.ACTIVITIES = (Button) findViewById(R.id.ACTIVITESEV);
        this.EVENT = (Button) findViewById(R.id.EVENEMENTEV);
        this.ME = (Button) findViewById(R.id.MEEV);

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

        EVENT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent otherActivity = new Intent(getApplicationContext(), EventActivity.class);
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
    }
    private void showEmployee(){
        JSONObject jsonObject = null;
        ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String, String>>();
        try {
            jsonObject = new JSONObject(JSON_STRING);
            JSONArray result = jsonObject.getJSONArray(TAG_JSON_ARRAY);

            for(int i = 0; i<result.length(); i++){
                JSONObject jo = result.getJSONObject(i);
                String id = jo.getString(TAG_ID);
                String nameactivity = jo.getString(TAG_NAME);
                String location = jo.getString(TAG_LOC);
                String description = jo.getString(TAG_DES);
                //          String lienimg = jo.getString(TAG_IMG);

                HashMap<String,String> employees = new HashMap<>();

                employees.put(TAG_ID,id);
                employees.put(TAG_NAME,nameactivity);
                employees.put(TAG_LOC,location);
                employees.put(TAG_DES,description);
                //        employees.put(TAG_IMG,lienimg);

                list.add(employees);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        ListAdapter adapter = new SimpleAdapter(
                EventActivity.this, list, R.layout.list_item_event,
                new String[]{
                        TAG_ID,
                        TAG_NAME,
                        TAG_LOC,
                        TAG_DES,
                        //              TAG_IMG
                },
                new int[]{
                        R.id.id,
                        R.id.nameactivity,
                        R.id.location,
                        R.id.description,
                        //            R.id.lienimg
                });

        listView.setAdapter(adapter);
    }

    private void getJSON(){
        class GetJSON extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(EventActivity.this,"Fetching Data","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                JSON_STRING = s;
                showEmployee();
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequest(URL_GET_ALL);
                return s;
            }
        }
        GetJSON gj = new GetJSON();
        gj.execute();
    }

}
