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
import com.bongamnguni.hogwarts.Model.ColorsModel;
import com.bongamnguni.hogwarts.Model.House;
import com.bongamnguni.hogwarts.Model.MembersModel;
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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class HouseInfo extends AppCompatActivity {
    TextView tvMascot, tvFounder, tvHeadOfhouse, tvHouseGhost;
    LinearLayoutManager linearlayout;
    RecyclerView recyclerView;
    HouseAdapter houseViewAdapter;
    private ArrayList<String> arrayList, arrayListMembers;
    private ListView listViewColors, listViewMembers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house_info);
        Bundle bundle = getIntent().getExtras();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout toolBarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        listViewColors = findViewById(R.id.listview);
        listViewMembers = findViewById(R.id.Memberslistview);

        listViewMembers.setVisibility(View.GONE);

        Config.TAG_HOUSE_NAME = bundle.getString(Config.TAG_HOUSE_NAME);
        toolBarLayout.setTitle(Config.TAG_HOUSE_NAME);
        Config.TAG_MASCOT = bundle.getString(Config.TAG_MASCOT);
        Config.TAG_HEAD_OF_HOUSE = bundle.getString(Config.TAG_HEAD_OF_HOUSE);
        Config.TAG_HOUSE_GHOST = bundle.getString(Config.TAG_HOUSE_GHOST);
        Config.TAG_FOUNDER = bundle.getString(Config.TAG_FOUNDER);

        arrayList = new ArrayList<>();
        arrayListMembers = new ArrayList<>();

        ArrayList<ColorsModel> arrayListColors = this.getIntent().getParcelableArrayListExtra(Config.TAG_COLORS);
        ArrayList<MembersModel> arrayListMember = this.getIntent().getParcelableArrayListExtra(Config.TAG_MEMBERS);


        for (int i = 0; i < arrayListMember.size(); i++) {
            arrayListMembers.add(arrayListMember.get(i).getMember());
        }

        LayoutInflater inflater = getLayoutInflater();
        ViewGroup header = (ViewGroup) inflater.inflate(R.layout.layout_member_header, listViewMembers, false);
        listViewMembers.addHeaderView(header, null, false);

        ArrayAdapter<String> membersAdapter = new ArrayAdapter<String>(getApplication(), android.R.layout.simple_list_item_1, android.R.id.text1, arrayListMembers);
        listViewMembers.setAdapter(membersAdapter);

        for (int i = 0; i < arrayListColors.size(); i++) {
            arrayList.add(arrayListColors.get(i).getColor());
        }

        LayoutInflater colorsInflater = getLayoutInflater();
        ViewGroup colorsHeader = (ViewGroup) colorsInflater.inflate(R.layout.layout_color_header, listViewColors, false);
        listViewColors.addHeaderView(colorsHeader, null, false);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplication(), android.R.layout.simple_list_item_1, android.R.id.text1, arrayList);
        listViewColors.setAdapter(adapter);

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
                listViewMembers.setVisibility(View.VISIBLE);
                Animation upAnim = AnimationUtils.loadAnimation(HouseInfo.this, R.anim.translate);
                upAnim.reset();
                listViewMembers.clearAnimation();
                listViewMembers.setAnimation(upAnim);
            }
        });
    }
}