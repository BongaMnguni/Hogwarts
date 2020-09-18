package com.bongamnguni.hogwarts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.bongamnguni.hogwarts.Adapter.HouseAdapter;
import com.bongamnguni.hogwarts.Model.House;
import com.bongamnguni.hogwarts.Utility.Config;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    LinearLayoutManager linearlayout;
    RecyclerView recyclerView;
    HouseAdapter houseViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.listHouses);

        linearlayout = new LinearLayoutManager(getApplicationContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearlayout);


        getData();
    }


    private void getData() {
        final List<House> arrList = new ArrayList<>();

        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, Config.BASE_URL+"houses/?key="+Config.API_KEY, null,
                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {

                        for (int i = 0; i < response.length(); i++) {

                            try {

                                JSONObject jsonObject = response.getJSONObject(i);
                                Gson gson = new Gson();

                                House gobalPojo = gson.fromJson(jsonObject.toString(), House.class);

                                    arrList.add(gobalPojo);

                            } catch (JSONException e) {
                                Toast.makeText(MainActivity.this, e.toString(), Toast.LENGTH_LONG).show();
                            }
                        }
                        houseViewAdapter = new HouseAdapter(getApplicationContext(),arrList);
                        recyclerView.setAdapter(houseViewAdapter);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Rest error", error.toString());
                        Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }
        );

        jsonArrayRequest.setRetryPolicy(new DefaultRetryPolicy(
                50000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT)
        );

        requestQueue.add(jsonArrayRequest);
    }









   /* private void getHouses(){

        JSONObject jsonObject = null;
        List<House> arrList = new ArrayList<>();
        try {
            jsonObject = new JSONObject(JSON_STRING);
            JSONArray result = jsonObject.getJSONArray(Config.TAG_JSON_ARRAY);

            for(int i = 0; i<result.length(); i++){
                JSONObject jo = result.getJSONObject(i);

                name = jo.getString(Config.TAG_HOUSE_NAME);

                arrList.add(new House(name));

            }
            houseViewAdapter = new HouseAdapter(getApplicationContext(),arrList);
            recyclerView.setAdapter(houseViewAdapter);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void getJSON(){

        class GetJSON extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                loading = new ProgressDialog(getApplicationContext());
                loading.setMessage("Please wait...");
                loading.setIndeterminate(false);
                loading.setCancelable(true);
            //    loading.show();

            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                JSON_STRING = s;
                getHouses();
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequest(Config.BASE_URL+"house/?key="+Config.API_KEY);
                return s;
            }
        }
        GetJSON gj = new GetJSON();
        gj.execute();
    }*/
}