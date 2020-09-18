package com.bongamnguni.hogwarts.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bongamnguni.hogwarts.Model.Student;
import com.bongamnguni.hogwarts.R;

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
                Toast.makeText(context,"hello",Toast.LENGTH_LONG).show();
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
