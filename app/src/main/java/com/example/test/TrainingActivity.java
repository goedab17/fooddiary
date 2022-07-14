package com.example.test;

import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test.bl.TrainingAdapter;

public class TrainingActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.traininglist);
        TrainingAdapter adapter = new TrainingAdapter();
        RecyclerView rvList = findViewById(R.id.rvTraining);

        rvList.setLayoutManager(new LinearLayoutManager(this));
        rvList.setAdapter(adapter);

        /*ItemTouchHelper itemTouchHelper = new ItemTouchHelper((new MyItemTouchHelper(adapter)));
        itemTouchHelper.attachToRecyclerView(rvList);*/

        SearchView svSearch = findViewById(R.id.svTraining);
        svSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.filterTraining(newText);
                return true;
            }
        });
        //ItemTouchHelper itemTouchHelper = new ItemTouchHelper();
    }

    private class MyItemTouchHelper extends ItemTouchHelper.SimpleCallback {
        private TrainingAdapter trainingAdapter;


        public MyItemTouchHelper(TrainingAdapter trainingAdapter) {
            super(0, ItemTouchHelper.RIGHT);
            this.trainingAdapter = trainingAdapter;
        }

        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

        }
    }

}
