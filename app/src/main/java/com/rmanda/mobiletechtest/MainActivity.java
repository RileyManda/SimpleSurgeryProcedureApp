    package com.rmanda.mobiletechtest;

    /**
     * Created by RMB on 2/21/18.
     */
    import android.content.Context;
    import android.content.Intent;
    import android.graphics.Color;
    import android.net.ConnectivityManager;
    import android.net.NetworkInfo;
    import android.os.AsyncTask;
    import android.os.Bundle;
    import android.support.design.widget.Snackbar;
    import android.support.v7.app.AppCompatActivity;
    import android.support.v7.widget.LinearLayoutManager;
    import android.support.v7.widget.RecyclerView;
    import android.util.Log;
    import android.view.View;
    import android.widget.ListAdapter;
    import android.widget.ProgressBar;
    import android.widget.SimpleAdapter;
    import android.widget.Toast;
    import org.json.JSONArray;
    import org.json.JSONException;
    import org.json.JSONObject;
    import java.util.ArrayList;
    import java.util.HashMap;

        public class MainActivity extends AppCompatActivity {
            private String TAG = MainActivity.class.getSimpleName();
            ArrayList<HashMap<String, String>> procedureList;
            private RecyclerView mRecyclerView;
            private RecyclerView.Adapter mAdapter;
            private RecyclerView.LayoutManager mLayoutManager;
            private ProgressBar progressBar;

            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_main);
                procedureList = new ArrayList<>();
                mRecyclerView = findViewById(R.id.rv_list_items);
                mRecyclerView.setHasFixedSize(true);
                //linear layout manager
                mLayoutManager = new LinearLayoutManager(this);
                mRecyclerView.setLayoutManager(mLayoutManager);
                progressBar = findViewById(R.id.progress);
                //app first run preferences:SplashScreen
                new GetProcedures().execute();
                Boolean isFirstRun = getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                        .getBoolean("isFirstRun", true);
                if (isFirstRun) {
                    startActivity(new Intent(MainActivity.this, SplashScreen.class));
                    Toast.makeText(MainActivity.this, "Hello,Welcome", Toast.LENGTH_LONG)
                            .show();
                }
                getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit()
                        .putBoolean("isFirstRun", false).apply();

                connectionMessage();


            }
            private class GetProcedures extends AsyncTask<Void, Void, Void> {
                @Override
                protected void onPreExecute() {
                    super.onPreExecute();
                    progressBar.setVisibility(View.VISIBLE);

                }

                @Override
                protected Void doInBackground(Void... arg0) {
                    HttpHandler sh = new HttpHandler();
                    // Making a request to url and getting response
                   String url = "https://api.myjson.com/bins/1eundp";
                    //String url = "https://192.168.100.30/db.json";

                    String jsonStr = sh.makeServiceCall(url);
                    Log.e(TAG, "Response from url: " + jsonStr);
                    if (jsonStr != null) {
                        try {
                            JSONObject jsonObj = new JSONObject(jsonStr);

                            // Getting JSON Array node
                            JSONArray procedures = jsonObj.getJSONArray("procedures");
                            JSONArray procedureDetails = jsonObj.getJSONArray("procedure_details");
                            // looping through All nodes
                            for (int i = 0; i < procedures.length(); i++) {
                                JSONObject c = procedures.getJSONObject(i);
                                String id = c.getString("id");
                                String name = c.getString("name");
                                String icon = c.getString("icon");

                                // hash map for single item
                                HashMap<String, String> procedure = new HashMap<>();
                                // adding each child node to HashMap key => value
                                procedure.put("id", id);
                                procedure.put("name", name);
                                procedure.put("icon", icon);

                                for(int j = 0; j < procedureDetails.length(); j ++) {
                                    JSONObject detail = procedureDetails.getJSONObject(j);
                                    if(detail.getString("id").equals(id)) {
                                        procedure.put("card", detail.getString("card"));
                                    }
                                    if(detail.getString("id").equals(id)) {
                                        procedure.put("card", detail.getString("card"));
                                    }
                                }
                                // adding data to list
                                procedureList.add(procedure);
                            }
                        } catch (final JSONException e) {
                            Log.e(TAG, "Json parsing error: " + e.getMessage());
                           runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getApplicationContext(),
                                            "Json parsing error: " + e.getMessage(),
                                            Toast.LENGTH_LONG).show();
                                }
                            });

                        }

                    } else {
                        Log.e(TAG, "Couldn't get json from server.");
                        runOnUiThread(() -> Toast.makeText(getApplicationContext(),
                                "Couldn't get json from server. Check LogCat for possible errors!",
                                Toast.LENGTH_LONG).show());
                    }

                    return null;
                }

                @Override
                protected void onPostExecute(Void result) {
                    super.onPostExecute(result);
                    ListAdapter adapter = new SimpleAdapter(MainActivity.this, procedureList,
                            R.layout.list_item, new String[]{"id", "name", "icon"},
                            new int[]{R.id.id, R.id.name, R.id.icon});
                    mAdapter = new RecycleAdapter(procedureList);
                    mRecyclerView.setAdapter(mAdapter);
                    progressBar.setVisibility(View.GONE);

                }
            }

            private boolean connected(){
                ConnectivityManager connectivityManager=(ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
                return activeNetworkInfo !=null && activeNetworkInfo.isConnected();


            }
            private void connectionMessage(){

                if(connected()){
                    Snackbar.make(findViewById(R.id.activity_main), "Your Internet Connection is Great",
                            Snackbar.LENGTH_LONG)
                            .setAction("Action", null).setActionTextColor(Color.RED).show();
                    Log.i("TRUE","User is connected");

                }else{
                    Snackbar.make(findViewById(R.id.activity_main), "Your You are not Connected to the internet",
                            Snackbar.LENGTH_LONG)
                            .setAction("Action", null).setActionTextColor(Color.RED).show();
                    Log.i("TRUE","User is not connected");
                }
            }




            }



