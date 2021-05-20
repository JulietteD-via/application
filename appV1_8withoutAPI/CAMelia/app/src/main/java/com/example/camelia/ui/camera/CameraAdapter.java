package com.example.camelia.ui.camera;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.camelia.R;
import com.example.camelia.ui.plants.PlantsAdapter;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CameraAdapter extends RecyclerView.Adapter<CameraAdapter.ViewHolder>{

    CameraAdapter.OnListItemClickListener listener;
    //List<Result> resultList;

    @NonNull
    @NotNull
    @Override
    public CameraAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view =inflater.inflate(R.layout.id_result, parent, false);
        return new CameraAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull CameraAdapter.ViewHolder holder, int position) {
        /*holder.imageView.setImageResource(resultList.get(position).getSimilarPicture());
        holder.textView.setText(resultList.get(position).getPlant_name());
        holder.progressBar.setProgress(resultList.get(position).getCertainty());*/
    }

    @Override
    public int getItemCount() {
        //return resultList.size();
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView imageView;
        public TextView textView;
        public ProgressBar progressBar;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            /*itemView.setOnClickListener(v -> listener.onClick(resultList.get(getAbsoluteAdapterPosition()).getPlant_name()));
            textView=itemView.findViewById(R.id.result_name);
            imageView=itemView.findViewById(R.id.result_picture);
            progressBar=itemView.findViewById(R.id.result_certainty);*/
        }
    }

    public interface OnListItemClickListener{
        void onClick(String plant_name);
    }
}
