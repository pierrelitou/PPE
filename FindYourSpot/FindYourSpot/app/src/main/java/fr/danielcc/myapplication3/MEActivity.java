package fr.danielcc.myapplication3;

import android.app.ProgressDialog;
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
    public static final String URL_GET_ALL = "http://192.168.43.192/findyourspot/GetMe.php";

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_me);
        //Pour mettre la photo de profil de l'utilisateur
        //photoprofil=(ImageView) findViewById(R.id.photoprofil);
        //photoprofil.setImageResource(R.drawable.photoprofil);
        listView = (ListView) findViewById(R.id.listView);

        //listView.setOnItemClickListener(this);
        getJSON();

        this.MAP = (Button) findViewById(R.id.CARTEME);
        this.ACTIVITIES = (Button) findViewById(R.id.ACTIVITESME);
        this.EVENT = (Button) findViewById(R.id.EVENEMENTME);
        this.ME = (Button) findViewById(R.id.MEME);

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
    }

    private void showEmployee(){
        JSONObject jsonObject = null;
        ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String, String>>();
        try {
            jsonObject = new JSONObject(JSON_STRING);
            JSONArray result = jsonObject.getJSONArray(TAG_JSON_ARRAY);

            for(int i = 0; i<result.length(); i++){
                JSONObject jo = result.getJSONObject(i);
                String firstname = jo.getString(TAG_firstname);
                String lastname = jo.getString(TAG_lastname);
                String dateofbirth = jo.getString(TAG_dateofbirth);
                String pseudo = jo.getString(TAG_pseudo);
                //          String lienimg = jo.getString(TAG_IMG);

                HashMap<String,String> employees = new HashMap<>();

                employees.put(TAG_firstname,firstname);
                employees.put(TAG_lastname,lastname);
                employees.put(TAG_dateofbirth,dateofbirth);
                employees.put(TAG_pseudo,pseudo);
                //        employees.put(TAG_IMG,lienimg);

                list.add(employees);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        ListAdapter adapter = new SimpleAdapter(
                MEActivity.this, list, R.layout.list_item_me,
                new String[]{
                        TAG_firstname,
                        TAG_lastname,
                        TAG_dateofbirth,
                        TAG_pseudo,
                        //              TAG_IMG
                },
                new int[]{
                        R.id.firstname,
                        R.id.lastname,
                        R.id.dateofbirth,
                        R.id.pseudo,
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
                loading = ProgressDialog.show(MEActivity.this,"Fetching Data","Wait...",false,false);
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

