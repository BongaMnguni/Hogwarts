package com.bongamnguni.hogwarts.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bongamnguni.hogwarts.HouseInfo;
import com.bongamnguni.hogwarts.Model.House;
import com.bongamnguni.hogwarts.R;
import com.bongamnguni.hogwarts.Utility.Config;

import java.util.List;


public class HouseAdapter extends RecyclerView.Adapter<HouseAdapter.HouseviewHolder>{
    public List<House> list ;
    private Context context;


    public HouseAdapter(Context context, List<House> list) {
        this.list = list;
        this.context = context;

    }
    @Override
    public HouseviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.house_cardview,null);
        HouseviewHolder viewHolder = new HouseviewHolder(v);

        return viewHolder;
    }
    @Override
    public void onBindViewHolder(final HouseviewHolder holder, final int position) {


        holder.tvHouseName.setText(list.get(position).getName());

        holder.tvHouseName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, HouseInfo.class);
                i.putExtra(Config.TAG_HOUSE_NAME,list.get(position).getName());
                i.putExtra(Config.TAG_MASCOT,list.get(position).getMascot());
                i.putExtra(Config.TAG_HEAD_OF_HOUSE,list.get(position).getHeadOfHouse());
                i.putExtra(Config.TAG_HOUSE_GHOST,list.get(position).getHouseGhost());
                i.putExtra(Config.TAG_FOUNDER,list.get(position).getFounder());
                //i.putExtra(Config.TAG_COLORS,list.get(position).getColors());
                context.startActivity(i);


            }
        });


    }
    @Override
    public int getItemCount() {
        return this.list.size();
    }

        public class HouseviewHolder extends RecyclerView.ViewHolder{

            TextView tvHouseName;

            public HouseviewHolder(@NonNull View itemView) {
                super(itemView);
                tvHouseName = itemView.findViewById(R.id.tvhouse_name);

            }
        }



}
