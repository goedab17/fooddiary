package com.example.test.bl;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test.EditFoodActivity;
import com.example.test.EditTrainingActivity;
import com.example.test.FoodActivity;
import com.example.test.TrainingActivity;

public class FoodViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private TextView tvFoodName;
    private TextView tvAmount;

    public FoodViewHolder(@NonNull View itemView, TextView tvFoodName, TextView tvAmount) {
        super(itemView);
        this.tvFoodName = tvFoodName;
        this.tvAmount = tvAmount;
        this.itemView.setOnClickListener(this);
    }

    public TextView getTvFoodName() {
        return tvFoodName;
    }

    public void setTvFoodName(TextView tvFoodName) {
        this.tvFoodName = tvFoodName;
    }

    public TextView getTvAmount() {
        return tvAmount;
    }

    public void setTvAmount(TextView tvAmount) {
        this.tvAmount = tvAmount;
    }

    @Override
    public void onClick(View v) {
        Intent intent= new Intent(itemView.getContext(), EditFoodActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_FORWARD_RESULT);
        intent.putExtra("foodname",tvFoodName.getText().toString());
        intent.putExtra("calories",Integer.parseInt(tvAmount.getText().toString()));

        itemView.getContext().startActivity(intent);
        ((FoodActivity)itemView.getContext()).finish();
        System.out.println("in here");
    }

}
