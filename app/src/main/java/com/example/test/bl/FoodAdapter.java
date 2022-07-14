package com.example.test.bl;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test.R;

import java.util.LinkedList;
import java.util.List;

public class FoodAdapter extends RecyclerView.Adapter<FoodViewHolder> {
    private List<Food> filtered = new LinkedList<>();
    private List<Food> foods = new LinkedList<>();

    public FoodAdapter() {
        foods.add(new Food("Ananas",59));
        foods.add(new Food("Apfel",59));
        foods.add(new Food("Aprikose",48));
        foods.add(new Food("Gebratene Asia-Nudeln",342));
        foods.add(new Food("Cheeseburger",254));
        foods.add(new Food("Chicken Wings",191));
        foods.add(new Food("Döner ",141));
        foods.add(new Food("Hot Dog",170));
        foods.add(new Food("Balisto",499));
        foods.add(new Food("Eierwaffel",341));
        foods.add(new Food("Gebrannte Mandeln",503));
        foods.add(new Food("Haferkeks",415));
        foods.add(new Food("Nutella",539));
        foods.add(new Food("Popcorn",364));
        foods.add(new Food("Studentenfutter",575));
        foods.add(new Food("Apfelsaft",48));
        foods.add(new Food("Orangensaft ",42));
        foods.add(new Food("Aperol Spritz",140));
        foods.add(new Food("Coca Cola",42));
        foods.add(new Food("Coca Cola Zero",0));
        foods.add(new Food("Capuccino",47));
        foods.add(new Food("Eiskaffee",70));
        foods.add(new Food("Grünter Tee",1));
        foods.add(new Food("Bananenbrot",311));
        foods.add(new Food("Brownie",430));
        foods.add(new Food("Marmorkuchen",380));
        foods.add(new Food("Käsekuchen",293));
        foods.add(new Food("Schwarzwälder Kirschtorte",334));
        foods.add(new Food("Haferflocken",372));
        foods.add(new Food("Reis",352));
        foods.add(new Food("Weizenmehl",348));
        foods.add(new Food("Toastbrot",258));
        foods.add(new Food("Zwieback",401));
        foods.add(new Food("Blätterteig",372));
        foods.add(new Food("Erbsen, gekocht",85));
        foods.add(new Food("Kichererbsen, gekocht",88));
        foods.add(new Food("Milch, 3,8 % Fett",65));
        foods.add(new Food("Naturjoghurt",62));
        foods.add(new Food("Mais",71));
        foods.add(new Food("Eisbergsalat",14));
        filtered= new LinkedList<>(foods);
    }

    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.food_item,parent,false);
        TextView tvFoodName = view.findViewById(R.id.tvFoodName);
        TextView tvNumber = view.findViewById(R.id.tvKcalPerGramm);

        return new FoodViewHolder(view,tvFoodName,tvNumber);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodViewHolder holder, int position) {
        Food food = filtered.get(position);
        holder.getTvFoodName().setText(food.getName());
        holder.getTvAmount().setText(food.getCalories()+"");
    }

    @Override
    public int getItemCount() {
        return filtered.size();
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void filterFood(String filter) {
        filtered = new LinkedList<>(foods);
        filtered.removeIf((food -> !food.getName().toLowerCase().
                contains(filter.toLowerCase())));
        notifyDataSetChanged();

    }



}
