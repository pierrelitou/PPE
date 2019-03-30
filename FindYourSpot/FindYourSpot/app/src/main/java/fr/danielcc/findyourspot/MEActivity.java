package fr.danielcc.findyourspot;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Button;
import android.view.View;

import android.content.Intent;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class MEActivity extends AppCompatActivity{

    public static final String URL_GET_ALL = Server.URL + "GetMyActivities.php";

    //JSON TAGs
    public static final String TAG_JSON_ARRAY="result";
    public static final String TAG_MY_ACTIVITIES = "my_activities";


    TextView firstname;
    TextView lastname;
    TextView dateofbirth;
    TextView pseudo;

    private ListView listView;
    private String JSON_STRING;

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

        firstname = (TextView) findViewById(R.id.me_firstname);
        lastname = (TextView) findViewById(R.id.me_lastname);
        dateofbirth = (TextView) findViewById(R.id.me_dateofbirth);
        pseudo = (TextView) findViewById(R.id.me_pseudo);

        firstname.setText(Server.firstname);
        lastname.setText(Server.lastname);
        dateofbirth.setText(Server.dateofbirth);
        pseudo.setText(Server.pseudo);

        //listView = (ListView) findViewById(R.id.listView);
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

        ME.setTextColor(getApplicationContext().getResources().getColor(R.color.ColorTextActivityEnable));
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
                Server.iduser="";
                editor.commit();
                startActivity(otherActivity);
                finish();
            }
        });

        //DisplayData();
    }

    private void DisplayData(){
        JSONObject jsonObject = null;
        ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String, String>>();
        try {
            jsonObject = new JSONObject(JSON_STRING);
            JSONArray result = jsonObject.getJSONArray(TAG_JSON_ARRAY);

            for(int i = 0; i<result.length(); i++){
                JSONObject jo = result.getJSONObject(i);
                String my_activities = jo.getString(TAG_MY_ACTIVITIES);

                HashMap<String,String> data_profil = new HashMap<>();

                data_profil.put(TAG_MY_ACTIVITIES,my_activities);

                list.add(data_profil);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        ListAdapter adapter = new SimpleAdapter(
                MEActivity.this, list, R.layout.list_item_me,
                new String[]{
                        TAG_MY_ACTIVITIES,
                },
                new int[]{
                        R.id.my_activities,
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


}

