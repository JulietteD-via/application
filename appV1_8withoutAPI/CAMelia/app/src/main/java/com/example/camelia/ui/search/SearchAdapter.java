package com.example.camelia.ui.search;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.camelia.Plant;
import com.example.camelia.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder>{

    List<Plant> plantList;
    OnListItemClickListener listener;

    public SearchAdapter (List<Plant> plantList, OnListItemClickListener listener){
        this.plantList=plantList;
        this.listener=listener;
    }

    @NonNull
    @NotNull
    @Override
    public SearchAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view =inflater.inflate(R.layout.search_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull SearchAdapter.ViewHolder holder, int position) {
        holder.imageView.setImageResource(plantList.get(position).getPictureId());
        holder.textView.setText(plantList.get(position).getName());

    }

    @Override
    public int getItemCount() {
        return plantList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView imageView;
        public TextView textView;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(v -> listener.onClick(plantList.get(getAbsoluteAdapterPosition()).getId()));
            textView=itemView.findViewById(R.id.plant_name);
            imageView=itemView.findViewById(R.id.plant_picture);
        }
    }

    public interface OnListItemClickListener{
        void onClick(int plant_id);
    }

}
