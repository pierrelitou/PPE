package fr.danielcc.myapplication3;


import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URI;
import java.util.HashMap;


public class ViewActivities extends AppCompatActivity{

    private EditText editTextId;
    private TextView editTextName;
    private TextView editTextLocation;
    private TextView editTextDescription;

    //private Button buttonUpdate;
    //private Button buttonDelete;

    private String id;

    public static final String TAG_NAME = "nameactivity";
    public static final String TAG_DES = "description";
    public static final String TAG_LOC = "location";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        Intent intent = getIntent();
        //
        id = intent.getStringExtra("act_id");
        //editTextId = (EditText) findViewById(R.id.editTextId);
        editTextName = (TextView) findViewById(R.id.editTextName);
        editTextLocation = (TextView) findViewById(R.id.editTextLocation);
        editTextDescription = (TextView) findViewById(R.id.editTextDescription);

        //buttonUpdate = (Button) findViewById(R.id.buttonUpdate);
        //buttonDelete = (Button) findViewById(R.id.buttonDelete);

        //buttonUpdate.setOnClickListener(this);
        //buttonDelete.setOnClickListener(this);

        //editTextId.setText(id);


        getEmployee();


    }

    private void getEmployee(){
        class GetEmployee extends AsyncTask<Void,Void,String>{
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(ViewActivities.this,"Fetching...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                showEmployee(s);
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequestParam("http://192.168.43.127/findyourspot/InfoActivities.php?id=",id);
                return s;
            }
        }
        GetEmployee ge = new GetEmployee();
        ge.execute();


    }

    private void showEmployee(String json) {
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray result = jsonObject.getJSONArray("result");
            JSONObject c = result.getJSONObject(0);

            String nameactivity = c.getString(TAG_NAME);

            String description = c.getString(TAG_DES);

            String location = c.getString(TAG_LOC);

            /*String harga_barang = c.getString(Config.TAG_DESG);
            String jenis_barang = c.getString(Config.TAG_SAL);*/

            editTextName.setText(nameactivity);
            /*editTextDesg.setText(harga_barang);*/
            editTextDescription.setText(description);
            editTextLocation.setText(location);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
/*
    private void updateEmployee(){
        final String nama_barang = editTextName.getText().toString().trim();
        final String harga_barang = editTextLocation.getText().toString().trim();
        final String jenis_barang = editTextDescription.getText().toString().trim();
*/
        /*class UpdateEmployee extends AsyncTask<Void,Void,String>{
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(ViewActivities.this,"Updating...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(ViewActivities.this,s,Toast.LENGTH_LONG).show();
            }
            @Override
            protected String doInBackground(Void... params) {
                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put(Config.KEY_EMP_ID,id);
                hashMap.put(Config.KEY_EMP_NAME,nama_barang);
                hashMap.put(Config.KEY_EMP_DESG,harga_barang);
                hashMap.put(Config.KEY_EMP_SAL,jenis_barang);

                RequestHandler rh = new RequestHandler();

                String s = rh.sendPostRequest(Config.URL_UPDATE_EMP,hashMap);

                return s;
            }
        }

        UpdateEmployee ue = new UpdateEmployee();

        ue.execute();
    }

    private void deleteEmployee(){
        class DeleteEmployee extends AsyncTask<Void,Void,String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(ViewActivities.this, "Updating...", "Wait...", false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(ViewActivities.this, s, Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequestParam(Config.URL_DELETE_EMP, id);
                return s;
            }
        }

        DeleteEmployee de = new DeleteEmployee();
        de.execute();
    }

    private void confirmDeleteEmployee(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Are You Sure You Want to Delete this Barang?");

        alertDialogBuilder.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        deleteEmployee();
                        startActivity(new Intent(ViewActivities.this,MainActivity2.class));
                    }
                });
        alertDialogBuilder.setNegativeButton("No",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();*/
 /*   }
    @Override
    public void onClick(View v) {
        if(v == buttonUpdate){
            updateEmployee();
        }

        if (v == buttonDelete){
            //confirmDeleteEmployee();
        }
    }*/
}

