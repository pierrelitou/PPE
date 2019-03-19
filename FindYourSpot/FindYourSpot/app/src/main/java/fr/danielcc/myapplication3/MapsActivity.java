package fr.danielcc.myapplication3;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.lang.Float;


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    //Link to database
    public static final String URL_GET_ALL = "http://192.168.43.82/htdocs/PPE/data/findyourspot/GetGPSPositions.php";

    //JSON Tagsvity
    public static final String TAG_JSON_ARRAY="result";
    public static final String TAG_LAT = "Lat";
    public static final String TAG_LNG = "Lng";
    public static final String TAG_TITLE = "nameactivity";
    public static final String TAG_DESC = "description";
    public static final String TAG_DATE = "dateevent";

    private String JSON_STRING;

    private GoogleMap mMap;
    private Button activity;
    private Button EVENT;
    private Button MAP;
    private Button ME;

    public void alertGPS(){
        final LocationManager manager = (LocationManager) getSystemService( Context.LOCATION_SERVICE );

        if ( !manager.isProviderEnabled( LocationManager.GPS_PROVIDER ) ) {
            buildAlertMessageNoGps();
        }
    }

    private void buildAlertMessageNoGps() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Your GPS seems to be disabled, do you want to enable it?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(@SuppressWarnings("unused") final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                        startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                        dialog.cancel();
                    }
                });
        final AlertDialog alert = builder.create();
        alert.show();
    }

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
                Intent otherActivity = new Intent(getApplicationContext(), MainActivity2.class);
                startActivity(otherActivity);
                finish();
            }
        });


        this.MAP = (Button) findViewById(R.id.CARTE);
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
    private ArrayList<activity> showEmployee() {
        JSONObject jsonObject = null;
        ArrayList< activity> list = new ArrayList< >();
        try {
            jsonObject = new JSONObject(JSON_STRING);
            JSONArray result = jsonObject.getJSONArray(TAG_JSON_ARRAY);

            for (int i = 0; i < result.length(); i++) {
                JSONObject jo = result.getJSONObject(i);
                String Lat = jo.getString(TAG_LAT);
                String Lng = jo.getString(TAG_LNG);
                String nameactivity = jo.getString(TAG_TITLE);
                String description = jo.getString(TAG_DESC);
                String dateevent = jo.getString(TAG_DATE);
                //          String lienimg = jo.getString(TAG_IMG);
                list.add(new activity(Float.parseFloat(Lat),Float.parseFloat(Lng),nameactivity,description,dateevent));
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
        //ArrayList<activity> act = showEmployee();
        alertGPS();
        MyLocationListener position = new MyLocationListener();
        googleMap.addMarker(new MarkerOptions().position(new LatLng(position.getLat(),position.getLgt())).title("your position").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)));
        ArrayList<activity> act = new ArrayList<>();
        act.add(new activity(48.888f,2.39216f,"activité de batard","ça va être cool tkt","00-00-00"));
        // Add a marker in Sydney and move the camera
        for(int i=0; i<act.size();i++){
            googleMap.addMarker(new MarkerOptions().position(new LatLng(act.get(i).getLat(),act.get(i).getLng())).title(act.get(i).getTitle()).snippet("date : "+act.get(i).getDate()+"\nDescription : "+act.get(i).getDesc()));

        }

    }
}
