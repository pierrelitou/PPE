package fr.danielcc.findyourspot;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.AdapterView;
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

public class Activities extends AppCompatActivity implements ListView.OnItemClickListener{

    //Link to database
    public static final String URL_GET_ALL = Server.URL + "GetActivities.php";

    //JSON Tagsvity
    public static final String TAG_JSON_ARRAY="result";
    public static final String TAG_ID = "id";
    public static final String TAG_NAME = "nameactivity";
    public static final String TAG_PROPOSEBY = "proposeby";
    public static final String TAG_LOC = "location";
    public static final String TAG_DES = "description";
    public static final String TAG_IMG = "lienimg";

    private ListView listView;
    private String JSON_STRING;

    private Button MAP;
    private Button ACTIVITIES;
    private Button EVENT;
    private Button ME;

    public ImageView imageactivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activities);
        listView = (ListView) findViewById(R.id.listView);
        listView.setOnItemClickListener(this);
        getJSON();

        this.MAP = (Button) findViewById(R.id.CARTEACT);
        this.ACTIVITIES = (Button) findViewById(R.id.ACTIVITESACT);
        this.EVENT = (Button) findViewById(R.id.EVENEMENTACT);
        this.ME = (Button) findViewById(R.id.MEACT);

        ACTIVITIES.setTextColor(getApplicationContext().getResources().getColor(R.color.ColorTextActivityEnable));

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


    }

    private void DisplayData(){
        JSONObject jsonObject = null;
        ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String, String>>();
        try {
            jsonObject = new JSONObject(JSON_STRING);
            JSONArray result = jsonObject.getJSONArray(TAG_JSON_ARRAY);

            for(int i = 0; i<result.length(); i++){
                JSONObject jo = result.getJSONObject(i);
                String id = jo.getString(TAG_ID);
                String nameactivity = jo.getString(TAG_NAME);
                String proposeby = jo.getString(TAG_PROPOSEBY);
                String location = jo.getString(TAG_LOC);
                String description = jo.getString(TAG_DES);

                HashMap<String,String> data_activities = new HashMap<>();

                data_activities.put(TAG_ID,id);
                data_activities.put(TAG_NAME,nameactivity);
                data_activities.put(TAG_PROPOSEBY,proposeby);
                data_activities.put(TAG_LOC,location);
                data_activities.put(TAG_DES,description);

                list.add(data_activities);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        ListAdapter adapter = new SimpleAdapter(
                Activities.this, list, R.layout.list_item_activity,
                new String[]{
                        //TAG_ID,
                        TAG_NAME,
                        //TAG_PROPOSEBY,
                        TAG_IMG,
                        TAG_LOC,
                        TAG_DES

                          },
                new int[]{
                        //R.id.id,
                        R.id.nameactivity,
                        //R.id.proposeby,
                        R.id.testbdd,
                        R.id.location,
                        R.id.description

                        });

        listView.setAdapter(adapter);
    }

    private void getJSON(){
        class GetJSON extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(Activities.this,"Fetching Data","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                JSON_STRING = s;
                DisplayData();
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

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Intent intent = new Intent(this, ViewActivities.class);
        HashMap<String,String> map = (HashMap)parent.getItemAtPosition(position);
        String empId = map.get(TAG_ID).toString();
        intent.putExtra("act_id",empId);
        startActivity(intent);
    }
}
