package com.bongamnguni.hogwarts;

import android.os.Bundle;

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
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class HouseInfo extends AppCompatActivity {
TextView tvMascot,tvFounder,tvHeadOfhouse,tvHouseGhost;
    LinearLayoutManager linearlayout;
    RecyclerView recyclerView;
    HouseAdapter houseViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house_info);
        Bundle bundle = getIntent().getExtras();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout toolBarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);

        Config.TAG_HOUSE_NAME= bundle.getString(Config.TAG_HOUSE_NAME);
        toolBarLayout.setTitle(Config.TAG_HOUSE_NAME);
        Config.TAG_MASCOT= bundle.getString(Config.TAG_MASCOT);
        Config.TAG_HEAD_OF_HOUSE= bundle.getString(Config.TAG_HEAD_OF_HOUSE);
        Config.TAG_HOUSE_GHOST= bundle.getString(Config.TAG_HOUSE_GHOST);
        Config.TAG_FOUNDER= bundle.getString(Config.TAG_FOUNDER);

        tvMascot = findViewById(R.id.tvmascot);
        tvHeadOfhouse = findViewById(R.id.tvheadOfhouse);
        tvHouseGhost = findViewById(R.id.tvhouseGhost);
        tvFounder = findViewById(R.id.tvfounder);

        tvMascot.setText(Config.TAG_MASCOT);
        tvHeadOfhouse.setText(Config.TAG_HEAD_OF_HOUSE);
        tvHouseGhost.setText(Config.TAG_HOUSE_GHOST);
        tvFounder.setText(Config.TAG_FOUNDER);



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private void getData() {
        final List<House> arrList = new ArrayList<>();
        RequestQueue requestQueue = Volley.newRequestQueue(HouseInfo.this);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, "https://www.potterapi.com/v1/houses/?key=$2a$10$1JEnmtEF417yBaFZcr51qukRjaKv8d5toEG5DKP/IUZWIVwfsaF7y", null,
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
                                Toast.makeText(HouseInfo.this, e.toString(), Toast.LENGTH_LONG).show();
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
                        Toast.makeText(HouseInfo.this, error.toString(), Toast.LENGTH_LONG).show();
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

}