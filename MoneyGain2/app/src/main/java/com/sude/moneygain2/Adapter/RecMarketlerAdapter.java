package com.sude.moneygain2.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sude.moneygain2.Model.RecMarketlerHelperClasses;
import com.sude.moneygain2.R;

import java.util.ArrayList;

public class RecMarketlerAdapter extends RecyclerView.Adapter<RecMarketlerAdapter.RecMarketViewHolder> {


    ArrayList<RecMarketlerHelperClasses> recMarketlerLocations;

    public RecMarketlerAdapter(ArrayList<RecMarketlerHelperClasses> recMarketlerLocations) {
        this.recMarketlerLocations = recMarketlerLocations;
    }

    @NonNull
    @Override
    public RecMarketViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.marketler_card_dizayn, parent, false);
        RecMarketViewHolder recMarketViewHolder = new RecMarketViewHolder(view);

        return recMarketViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecMarketViewHolder holder, int position) {

        RecMarketlerHelperClasses recMarketlerHelperClasses = recMarketlerLocations.get(position);

        holder.image.setImageResource(recMarketlerHelperClasses.getImage());
        holder.title.setText(recMarketlerHelperClasses.getTitle());
        holder.desc.setText(recMarketlerHelperClasses.getDescription());



    }

    @Override
    public int getItemCount() {
        return recMarketlerLocations.size();
    }


    public static class RecMarketViewHolder extends RecyclerView.ViewHolder{


        ImageView image;
        TextView title, desc;


        public RecMarketViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.rec_marketler_image);
            title = itemView.findViewById(R.id.rec_marketler_title);
            desc = itemView.findViewById(R.id.rec_marketler_desc);

        }
    }



}
