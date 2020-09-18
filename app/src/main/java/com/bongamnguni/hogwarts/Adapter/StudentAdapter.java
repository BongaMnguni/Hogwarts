package com.bongamnguni.hogwarts.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bongamnguni.hogwarts.HouseInfo;
import com.bongamnguni.hogwarts.Model.Student;
import com.bongamnguni.hogwarts.R;
import com.bongamnguni.hogwarts.Utility.Config;

import java.util.List;


public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentviewHolder>{
    public List<Student> list ;
    private Context context;


    public StudentAdapter(Context context, List<Student> list) {
        this.list = list;
        this.context = context;

    }
    @Override
    public StudentviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.student_cardview,null);
        StudentviewHolder viewHolder = new StudentviewHolder(v);

        return viewHolder;
    }
    @Override
    public void onBindViewHolder(final StudentviewHolder holder, final int position) {


        holder.tvStudentName.setText(list.get(position).getName());

        holder.tvStudentName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(context, HouseInfo.class);

                i.putExtra(Config.TAG_NAME,list.get(position).getName());
                i.putExtra(Config.TAG_HOUSE_NAME,list.get(position).getHouse());
                i.putExtra(Config.TAG_SCHOOL,list.get(position).getSchool());
                i.putExtra(Config.TAG_MINISTRY_OF_MAGIC,list.get(position).getMinistryOfMagic());
                i.putExtra(Config.TAG_ORDER_OF_THE_PHOENIX,list.get(position).getOrderOfThePhoenix());
                i.putExtra(Config.TAG_DUMBLEDORES_ARMY,list.get(position).getDumbledoresArmy());
                i.putExtra(Config.TAG_DEATH_EATER,list.get(position).getDeathEater());
                i.putExtra(Config.TAG_BLOOD_STATUS,list.get(position).getBloodStatus());
                i.putExtra(Config.TAG_SPECIES,list.get(position).getSpecies());

                context.startActivity(i);

            }
        });


    }
    @Override
    public int getItemCount() {
        return this.list.size();
    }

        public class StudentviewHolder extends RecyclerView.ViewHolder{

            TextView tvStudentName;

            public StudentviewHolder(@NonNull View itemView) {
                super(itemView);
                tvStudentName = itemView.findViewById(R.id.tvstudent_name);

            }
        }



}
