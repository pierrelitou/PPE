package fr.danielcc.findyourspot;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    //Link to database
    public static final String URL_GET_ALL = Server.URL + "GetGPSPositions.php";

    //JSON Tagsvity
    public static final String TAG_JSON_ARRAY="result";
    public static final String TAG_LAT = "Lat";
    public static final String TAG_LNG = "Lng";
    public static final String TAG_TITLE = "nameactivity";
    public static final String TAG_DESC = "description";

    private String JSON_STRING;

    private GoogleMap mMap;
    private Button activity;
    private Button EVENT;
    private Button MAP;
    private Button ME;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.

        //listView.setOnItemClickListener(this);
        getJSON();
        this.activity = (Button) findViewById(R.id.ACTIVITES);

        activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent otherActivity = new Intent(getApplicationContext(), Activities.class);
                startActivity(otherActivity);
                finish();
            }
        });


        this.MAP = (Button) findViewById(R.id.CARTE);

        MAP.setTextColor(getApplicationContext().getResources().getColor(R.color.ColorTextActivityEnable));

        MAP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent otherActivity = new Intent(getApplicationContext(), MapsActivity.class);
                startActivity(otherActivity);
                finish();
            }
        });

        this.EVENT = (Button) findViewById(R.id.EVENEMENT);
        EVENT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent otherActivity = new Intent(getApplicationContext(), EventActivity.class);
                startActivity(otherActivity);
                finish();
            }
        });

        this.ME = (Button) findViewById(R.id.MEMAP);
        ME.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent otherActivity = new Intent(getApplicationContext(), MEActivity.class);
                startActivity(otherActivity);
                finish();
            }
        });



        /*event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent otherActivity2 = new Intent(getApplicationContext(), activity_event.class);
                startActivity(otherActivity2);
                finish();
            }
        });*/

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    private ArrayList<HashMap<String, String>> showEmployee() {
        JSONObject jsonObject = null;
        //ArrayList<Activity> list = new ArrayList<>();
        ArrayList<HashMap<String, String>> list = new ArrayList<>();
        try {
            jsonObject = new JSONObject(JSON_STRING);
            JSONArray result = jsonObject.getJSONArray(TAG_JSON_ARRAY);

            for (int i = 0; i < result.length(); i++) {
                JSONObject jo = result.getJSONObject(i);

                String Lat = jo.getString(TAG_LAT);
                String Lng = jo.getString(TAG_LNG);
                String title = jo.getString(TAG_TITLE);
                String desc = jo.getString(TAG_DESC);


                HashMap<String, String> employees = new HashMap<>();

                employees.put(TAG_LAT, Lat);
                employees.put(TAG_LNG, Lng);
                employees.put(TAG_TITLE,title);
                employees.put(TAG_DESC,desc);

                //list.add(new Activity(Float.parseFloat(jo.getString(TAG_LAT)),Float.parseFloat(jo.getString(TAG_LNG)),jo.getString(TAG_TITLE),jo.getString(TAG_DESC)));
                list.add(employees);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }


    private void getJSON(){
        class GetJSON extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(MapsActivity.this,"Fetching Data","Wait...",false,false);
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
        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera. In this case,
         * we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to install
         * it inside the SupportMapFragment. This method will only be triggered once the user has
         * installed Google Play services and returned to the app.
         */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        ArrayList<Activity> act = new ArrayList<>();
        act.add(new Activity(48.888f,2.39216f,"Degustation de Vins","Degustation et decouverte de produits du terroir",0));
        act.add(new Activity(48.834f,2.33218f,"Randonnee","Une decouverte des catacombes de Paris qui vous fera peur",0));
        act.add(new Activity(48.8495f,2.28444f,"Bowling","Venez vous eclater avec moi au bowling",1));

        for(int i = 0; i<act.size() ; i++){
            if (act.get(i).getEvent()==1) {
                mMap.addMarker(new MarkerOptions().position(new LatLng(act.get(i).getLat(), act.get(i).getLng())).title(act.get(i).getTitle()).snippet(act.get(i).getDesc()).icon(BitmapDescriptorFactory
                        .defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
            }
            else{
                mMap.addMarker(new MarkerOptions().position(new LatLng(act.get(i).getLat(), act.get(i).getLng())).title(act.get(i).getTitle()).snippet(act.get(i).getDesc()));

            }
        }

        //mMap.addMarker(new MarkerOptions().position(new LatLng(10,10)).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(48.8517,2.2872)));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(11));

        // Add a marker in Sydney and move the camera

    }
}
