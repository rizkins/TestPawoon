package rinus.pawoon;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import agent.HttpListener;
import agent.HttpRequest;
import db.DBTes;
import db.OpenHelper;
import json.JSONArray;
import json.JSONException;
import json.JSONObject;
import models.TesDTO;
import utils.Contents;

public class MainActivity extends AppCompatActivity {

    ProgressDialog progress_dialog;
    private String returnMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Contents.DBHelper=new OpenHelper(this);

        getTesData();
    }

    public void getTesData(){
        new UploadTask().execute();
//        if (isOnline()) {
//            progress_dialog = ProgressDialog.show(MainActivity.this, "",
//                    "Menarik data. Mohon tunggu...", true);
//            HttpRequest.instance().
//                    request(progress_dialog, MainActivity.this, "http://jsonplaceholder.typicode.com/todos"
//                            , "tes=tes", 0);
//        } else {
//            showMessage("No Connection");
//        }
    }

    private class UploadTask extends AsyncTask<String, Void, Boolean> {

        private Exception exception;

        protected Boolean doInBackground(String... urls) {

            URL url;
            HttpURLConnection urlConnection = null;
            try {

                url = new URL("http://jsonplaceholder.typicode.com/todos");

                urlConnection = (HttpURLConnection) url
                        .openConnection();

                InputStream in = urlConnection.getInputStream();

                final StringBuffer buf = new StringBuffer();
                int k = 0;
                while((k = in.read())!=-1){
                    buf.append((char)k);
                }

                JSONObject jsonObject;
//                Log.d("Main","Tes :"+buf.toString());
                try {
                    jsonObject = new JSONObject(buf.toString());
                    DBTes dbt=new DBTes(MainActivity.this, Contents.getDBHelper().getDB());
                    dbt.deleteAll();
                    JSONArray arrTes=jsonObject.getJSONArray("");
                    for (int i=0; i<arrTes.length(); i++) {
                        JSONObject jsonTes = arrTes.getJSONObject(i);

                        TesDTO td=new TesDTO();
                        td.fromJSON(jsonTes.toString());
                        dbt.insert(td);
                    }
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    Log.d("Main", "e : " + e);
                }
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                return null;
            }
        }

        protected void onPostExecute(Boolean feed) {
            // TODO: check this.exception
            // TODO: do something with the feed

        }
    }

    public void showMessage(String message) {
        new AlertDialog.Builder(this).setMessage(message).setCancelable(true).show();

    }

    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnected()) {
            return true;
        }
        return false;
    }
}
