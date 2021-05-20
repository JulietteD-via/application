package com.example.camelia.ui.plants;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.camelia.Plant;
import com.example.camelia.R;
import com.example.camelia.ui.search.SearchAdapter;

import java.util.ArrayList;
import java.util.List;

public class PlantsFragment extends Fragment implements PlantsAdapter.OnListItemClickListener{

    private PlantsViewModel plantsViewModel;
    private RecyclerView plantsRecyclerView;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        plantsViewModel = new ViewModelProvider(this).get(PlantsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_plants, container, false);

        plantsRecyclerView=root.findViewById(R.id.my_plants);
        plantsRecyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        plantsRecyclerView.hasFixedSize();

        List<Plant> plantList=new ArrayList<Plant>();
        plantList.add(new Plant(1, "Fougères", "Filicophyta"));
        plantList.add(new Plant(1, "Fougères", "Filicophyta"));
        plantList.add(new Plant(1, "Fougères", "Filicophyta"));
        plantList.add(new Plant(1, "Fougères", "Filicophyta"));
        plantList.add(new Plant(1, "Fougères", "Filicophyta"));

        PlantsAdapter plantsAdapter = new PlantsAdapter(plantList, this);
        plantsRecyclerView.setAdapter(plantsAdapter);

        return root;
    }

    @Override
    public void onClick(int plant_id) {

    }
}
