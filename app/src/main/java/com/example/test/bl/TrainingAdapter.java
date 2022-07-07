package com.example.test.bl;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test.R;

import java.util.LinkedList;
import java.util.List;

public class TrainingAdapter extends RecyclerView.Adapter<TrainingViewHolder>{

    private List<Training> trainings;
    private List<Training> filtered;
    public TrainingAdapter() {
        filtered.add(new Training("Squash (Wettkampf)",30,960/30));
        filtered.add(new Training("Seilspringen",30,440/30));
        filtered.add(new Training("Joggen (8,5 km/h)",30,360/30));
        filtered.add(new Training("Boxen (Training)",30,320/30));
        filtered.add(new Training("Klettern",30,320/30));
        filtered.add(new Training("H.I.I.T",30,320/30));
        filtered.add(new Training("Rudern (100 Watt)",30,320/30));
        filtered.add(new Training("Schwimmen (Kraulen)",30,392/30));
        filtered.add(new Training("Fußball",30,320/30));
        filtered.add(new Training("Basketball",30,260/30));
        filtered.add(new Training("Winter-Langlauf (6,5km/h)",30,960/30));
        filtered.add(new Training("Spinning",30,340/30));
        filtered.add(new Training("Inlineskating (14,5 km/h)",30,300/30));
        filtered.add(new Training("Zumba",30,260/30));
        filtered.add(new Training("Badminton",30,220/30));
        filtered.add(new Training("Aerobic (Low impact)",30,200/30));
        filtered.add(new Training("Kraftsport",30,240/30));
        filtered.add(new Training("Crossfit",30,320/30));
        filtered.add(new Training("Nordic Walking",30,195/30));
        filtered.add(new Training("Radfahren (15 km/h)",30,232/30));
        filtered.add(new Training("Indoor Cycling 100 Watt)",30,272/30));
        filtered.add(new Training("Pilates",30,120/30));
        filtered.add(new Training("Volleyball",30,160/30));
        filtered.add(new Training("Tischtennis",30,160/30));
    }


    @NonNull
    @Override
    public TrainingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.training_item,parent,false);
        TextView tvName = view.findViewById(R.id.tvName);
        TextView tvNumber = view.findViewById(R.id.tvKcalPer);

        return new TrainingViewHolder(view,tvName,tvNumber);
    }

    @Override
    public void onBindViewHolder(@NonNull TrainingViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
