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
import com.bongamnguni.hogwarts.Adapter.HouseAdapter;
import com.bongamnguni.hogwarts.Adapter.StudentAdapter;
import com.bongamnguni.hogwarts.Model.House;
import com.bongamnguni.hogwarts.Model.Student;
import com.bongamnguni.hogwarts.Utility.Config;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class StudentActivity extends AppCompatActivity {

    LinearLayoutManager linearlayout;
    RecyclerView recyclerView;
    StudentAdapter studentViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        recyclerView = findViewById(R.id.listStudents);

        linearlayout = new LinearLayoutManager(getApplicationContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearlayout);


        getData();
    }


    private void getData() {
        final List<Student> arrList = new ArrayList<>();

        RequestQueue requestQueue = Volley.newRequestQueue(StudentActivity.this);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET,
                Config.BASE_URL+"characters/?key="+Config.API_KEY, null,

                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {

                        for (int i = 0; i < response.length(); i++) {

                            try {

                                JSONObject jsonObject = response.getJSONObject(i);
                                Gson gson = new Gson();

                                Student gobalPojo = gson.fromJson(jsonObject.toString(), Student.class);

                                if (gobalPojo.getRole() != null && gobalPojo.getRole().equalsIgnoreCase("student")){
                                    arrList.add(gobalPojo);
                                }


                            } catch (JSONException e) {
                                Toast.makeText(StudentActivity.this, e.toString(), Toast.LENGTH_LONG).show();
                            }
                        }
                        studentViewAdapter = new StudentAdapter(getApplicationContext(),arrList);
                        recyclerView.setAdapter(studentViewAdapter);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Rest error", error.toString());
                        Toast.makeText(StudentActivity.this, error.toString(), Toast.LENGTH_LONG).show();
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