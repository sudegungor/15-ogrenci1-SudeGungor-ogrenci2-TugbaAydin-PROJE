package com.sude.moneygain.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sude.moneygain.Model.UserHelperClass;
import com.sude.moneygain.R;

import java.util.ArrayList;

public class SearcAdapter extends RecyclerView.Adapter<SearcAdapter.RecSearchViewHolder> {


    ArrayList<UserHelperClass> recSearchLocations;


    public SearcAdapter(ArrayList<UserHelperClass> recSearchLocations) {
        this.recSearchLocations = recSearchLocations;
    }


    @NonNull
    @Override
    public RecSearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_user_search_item_view, parent, false);
        SearcAdapter.RecSearchViewHolder recSearchViewHolder = new SearcAdapter.RecSearchViewHolder(view);

        return recSearchViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecSearchViewHolder holder, int position) {

        UserHelperClass userHelperClass = recSearchLocations.get(position);

        holder.textViewName.setText(userHelperClass.getAd());
        holder.textViewNick.setText(userHelperClass.getNick());

        holder.textViewEmail.setText(userHelperClass.getEmail());
        holder.textViewTelno.setText(userHelperClass.getTelno());

    }

    @Override
    public int getItemCount()  {
        return recSearchLocations.size();
    }

    public class RecSearchViewHolder extends RecyclerView.ViewHolder {

        TextView textViewName, textViewNick, textViewEmail, textViewTelno;

        public RecSearchViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewName = itemView.findViewById(R.id.textViewName);
            textViewNick = itemView.findViewById(R.id.textViewNick);
            textViewEmail = itemView.findViewById(R.id.textViewEmail);
            textViewTelno = itemView.findViewById(R.id.textViewTelno);
        }
    }
}
