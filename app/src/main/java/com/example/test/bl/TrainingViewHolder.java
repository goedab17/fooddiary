package com.example.test.bl;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test.EditTrainingActivity;
import com.example.test.TrainingActivity;

public class TrainingViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private TextView tvName;
    private TextView tvNumber;
    private Training training;


    public TrainingViewHolder(@NonNull View itemView, TextView tvName, TextView tvNumber) {
        super(itemView);
        this.tvName = tvName;
        this.tvNumber = tvNumber;
        this.itemView.setOnClickListener(this);
    }



    public TextView getTvName() {
        return tvName;
    }

    public void setTvName(TextView tvName) {
        this.tvName = tvName;
    }

    public TextView getTvNumber() {
        return tvNumber;
    }

    public void setTvNumber(TextView tvNumber) {
        this.tvNumber = tvNumber;
    }

    public Training getTraining() {
        return training;
    }

    public void setTraining(Training training) {
        this.training = training;
    }

    @Override
    public void onClick(View v) {
        Intent intent= new Intent(itemView.getContext(), EditTrainingActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_FORWARD_RESULT);
        intent.putExtra("name",tvName.getText().toString());
        intent.putExtra("rate",Double.parseDouble(tvNumber.getText().toString()));

        itemView.getContext().startActivity(intent);
        ((TrainingActivity)itemView.getContext()).finish();
        System.out.println("in here");


    }

}
