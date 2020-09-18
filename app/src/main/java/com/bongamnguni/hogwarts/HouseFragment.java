package com.bongamnguni.hogwarts;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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


public class HouseFragment extends Fragment {
    private LinearLayoutManager linearlayout;
    private RecyclerView recyclerView;
    private HouseAdapter houseViewAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_house, container, false);

        recyclerView = view.findViewById(R.id.listHouses);

        linearlayout = new LinearLayoutManager(getActivity());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearlayout);

        getAllHouses();
        return view;
    }

    private void getAllHouses() {
        final List<House> arrList = new ArrayList<>();

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());

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
                                Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_LONG).show();
                            }
                        }
                        houseViewAdapter = new HouseAdapter(getActivity(),arrList);
                        recyclerView.setAdapter(houseViewAdapter);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Rest error", error.toString());
                        Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_LONG).show();
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