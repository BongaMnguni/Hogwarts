package com.bongamnguni.hogwarts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import com.bongamnguni.hogwarts.Adapter.SpellsAdapter;
import com.bongamnguni.hogwarts.Model.Spells;
import com.bongamnguni.hogwarts.Utility.Config;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SpellsInfo extends AppCompatActivity {
    LinearLayoutManager linearlayout;
    RecyclerView recyclerView;
    SpellsAdapter spellsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spells_info);

        recyclerView = findViewById(R.id.listSpells);

        linearlayout = new LinearLayoutManager(getApplicationContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearlayout);
    }
    private void getData() {
        final List<Spells> arrList = new ArrayList<>();

        RequestQueue requestQueue = Volley.newRequestQueue(SpellsInfo.this);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET,
                Config.BASE_URL+"spells/?key="+Config.API_KEY, null,

                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {

                        for (int i = 0; i < response.length(); i++) {

                            try {

                                JSONObject jsonObject = response.getJSONObject(i);
                                Gson gson = new Gson();

                                Spells gobalPojo = gson.fromJson(jsonObject.toString(), Spells.class);

                                    arrList.add(gobalPojo);


                            } catch (JSONException e) {
                                Toast.makeText(SpellsInfo.this, e.toString(), Toast.LENGTH_LONG).show();
                            }
                        }
                        spellsAdapter = new SpellsAdapter(getApplicationContext(),arrList);
                        recyclerView.setAdapter(spellsAdapter);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Rest error", error.toString());
                        Toast.makeText(SpellsInfo.this, error.toString(), Toast.LENGTH_LONG).show();
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