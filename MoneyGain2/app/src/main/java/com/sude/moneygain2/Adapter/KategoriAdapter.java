package com.sude.moneygain2.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sude.moneygain2.Model.CategoriesHelperClass;
import com.sude.moneygain2.R;
import com.sude.moneygain2.User.Kategoriler.BebekOyuncak;
import com.sude.moneygain2.User.Kategoriler.Deterjan;
import com.sude.moneygain2.User.Kategoriler.EtTavukBalik;
import com.sude.moneygain2.User.Kategoriler.GidaSekerleme;
import com.sude.moneygain2.User.Kategoriler.Icecek;
import com.sude.moneygain2.User.Kategoriler.SutKahvalti;

import java.util.ArrayList;

public class KategoriAdapter extends RecyclerView.Adapter<KategoriAdapter.AdapterAllCategoriesViewHolder> {

    static String username_email, pass;


    ArrayList<CategoriesHelperClass> en_cok_goruntulenenlerLocations;

    public KategoriAdapter(ArrayList<CategoriesHelperClass> en_cok_goruntulenenlerLocations) {
        this.en_cok_goruntulenenlerLocations = en_cok_goruntulenenlerLocations;
    }


    @NonNull
    @Override
    public AdapterAllCategoriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.kategori_card_dizayn, parent, false);
        AdapterAllCategoriesViewHolder lvh = new AdapterAllCategoriesViewHolder(view);
        return lvh;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterAllCategoriesViewHolder holder, int position) {

        CategoriesHelperClass helperClass = en_cok_goruntulenenlerLocations.get(position);
        holder.imageView.setImageResource(helperClass.getImage());
        holder.textView.setText(helperClass.getTitle());
        holder.relativeLayout.setBackground(helperClass.getGradientDrawable());
        username_email = helperClass.getUsername_email();
        pass = helperClass.getPass();

    }

    @Override
    public int getItemCount() {
        return en_cok_goruntulenenlerLocations.size();
    }

    public static class AdapterAllCategoriesViewHolder extends RecyclerView.ViewHolder{

        RelativeLayout relativeLayout;
        ImageView imageView;
        TextView textView;




        public AdapterAllCategoriesViewHolder(@NonNull View itemView) {
            super(itemView);

            relativeLayout = itemView.findViewById(R.id.background_gradient);
            imageView = itemView.findViewById(R.id.kategori_resim);
            textView = itemView.findViewById(R.id.kategori_title);


            relativeLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(textView.getText().equals("Et, Tavuk, Balık")){
                        Intent intent = new Intent(v.getContext(), EtTavukBalik.class);
                        intent.putExtra("username_email", username_email);
                        intent.putExtra("pass", pass);
                        v.getContext().startActivity(intent);
                    }

                    else if(textView.getText().equals("Süt, Kahvaltılık")){
                        Intent intent = new Intent(v.getContext(), SutKahvalti.class);
                        intent.putExtra("username_email", username_email);
                        intent.putExtra("pass", pass);
                        v.getContext().startActivity(intent);
                    }

                    else if(textView.getText().equals("Gıda, Şekerleme")){
                        Intent intent = new Intent(v.getContext(), GidaSekerleme.class);
                        intent.putExtra("username_email", username_email);
                        intent.putExtra("pass", pass);
                        v.getContext().startActivity(intent);
                    }

                    else if(textView.getText().equals("         İçecek")){
                        Intent intent = new Intent(v.getContext(), Icecek.class);
                        intent.putExtra("username_email", username_email);
                        intent.putExtra("pass", pass);
                        v.getContext().startActivity(intent);
                    }

                    else if(textView.getText().equals("Deterjan, Temizlik")){
                        Intent intent = new Intent(v.getContext(), Deterjan.class);
                        intent.putExtra("username_email", username_email);
                        intent.putExtra("pass", pass);
                        v.getContext().startActivity(intent);
                    }

                    else if(textView.getText().equals("Bebek, Oyuncak")){
                        Intent intent = new Intent(v.getContext(), BebekOyuncak.class);
                        intent.putExtra("username_email", username_email);
                        intent.putExtra("pass", pass);
                        v.getContext().startActivity(intent);
                    }

                }
            });



        }
    }


}
