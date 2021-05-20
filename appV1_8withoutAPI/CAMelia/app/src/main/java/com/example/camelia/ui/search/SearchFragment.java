package com.example.camelia.ui.search;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

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
import com.example.camelia.ui.PlantActivity;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment implements SearchAdapter.OnListItemClickListener{

    private SearchViewModel searchViewModel;
    private RecyclerView searchRecyclerView;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        searchViewModel = new ViewModelProvider(this).get(SearchViewModel.class);
        View root = inflater.inflate(R.layout.fragment_search, container, false);

        searchRecyclerView=root.findViewById(R.id.grid_search_results);
        searchRecyclerView.setLayoutManager(new GridLayoutManager(this.getContext(), 2));
        searchRecyclerView.hasFixedSize();

        List<Plant> plantList=new ArrayList<Plant>();
        plantList.add(new Plant(1, "Fougères", "Filicophyta"));
        plantList.add(new Plant(1, "Fougères", "Filicophyta"));
        plantList.add(new Plant(1, "Fougères", "Filicophyta"));
        plantList.add(new Plant(1, "Fougères", "Filicophyta"));
        plantList.add(new Plant(1, "Fougères", "Filicophyta"));

        SearchAdapter searchAdapter=new SearchAdapter(plantList, this);
        searchRecyclerView.setAdapter(searchAdapter);

        return root;
    }

    @Override
    public void onClick(int plant_id) {
        //Open plant activity and load corresponding data into the Views using the id field
        Toast toast=Toast.makeText(this.getContext(), "This should display a plant's details", Toast.LENGTH_SHORT);
        toast.show();

        //Intent

        //startActivity(intent);

    }
}
