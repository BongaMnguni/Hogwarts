package com.bongamnguni.hogwarts.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bongamnguni.hogwarts.HouseInfo;
import com.bongamnguni.hogwarts.Model.Spells;
import com.bongamnguni.hogwarts.Model.Student;
import com.bongamnguni.hogwarts.R;
import com.bongamnguni.hogwarts.Utility.Config;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class SpellsAdapter extends RecyclerView.Adapter<SpellsAdapter.SpellsviewHolder>{
    public List<Spells> list ;
    private Context context;


    public SpellsAdapter(Context context, List<Spells> list) {
        this.list = list;
        this.context = context;

    }
    @Override
    public SpellsviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.spells_cardview,null);
        SpellsviewHolder viewHolder = new SpellsviewHolder(v);

        return viewHolder;
    }
    @Override
    public void onBindViewHolder(final SpellsviewHolder holder, final int position) {


        holder.tvSpells.setText(list.get(position).getSpell());
        holder.tvType.setText(list.get(position).getType());
        holder.tvEffect.setText(list.get(position).getEffect());

        holder.tvSpells.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }
    @Override
    public int getItemCount() {
        return this.list.size();
    }

        public class SpellsviewHolder extends RecyclerView.ViewHolder{

            TextView tvSpells,tvType,tvEffect;

            public SpellsviewHolder(@NonNull View itemView) {
                super(itemView);
                tvSpells = itemView.findViewById(R.id.tvSpells);
                tvType = itemView.findViewById(R.id.tvType);
                tvEffect = itemView.findViewById(R.id.tvEffect);

            }
        }



}
