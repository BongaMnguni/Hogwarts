package com.bongamnguni.hogwarts.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bongamnguni.hogwarts.Model.House;
import com.bongamnguni.hogwarts.R;

import java.util.List;


public class HouseInfoAdapter extends RecyclerView.Adapter<HouseInfoAdapter.HouseviewHolder>{
    public List<House> list ;
    private Context context;


    public HouseInfoAdapter(Context context, List<House> list) {
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
                Toast.makeText(context,"hello",Toast.LENGTH_LONG).show();
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
