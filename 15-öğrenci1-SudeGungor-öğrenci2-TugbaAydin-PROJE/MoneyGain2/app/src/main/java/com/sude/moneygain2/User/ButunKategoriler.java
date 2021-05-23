package com.sude.moneygain2.User;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.sude.moneygain2.R;
import com.sude.moneygain2.User.Kategoriler.BebekOyuncak;
import com.sude.moneygain2.User.Kategoriler.Deterjan;
import com.sude.moneygain2.User.Kategoriler.GidaSekerleme;
import com.sude.moneygain2.User.Kategoriler.Icecek;
import com.sude.moneygain2.User.Kategoriler.SutKahvalti;

public class ButunKategoriler extends AppCompatActivity {

    ImageView backBtn;
    RelativeLayout butun_kategoriler_sut_kahvaltı,butun_kategoriler_gida_sekerleme,butun_kategoriler_icecek,butun_kategoriler_deterjan,butun_kategoriler_bebek_oyuncak;
    String username_email, pass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_butun_kategoriler);


        backBtn =findViewById(R.id.backBtn);
        butun_kategoriler_sut_kahvaltı = findViewById(R.id.butun_kategoriler_sut_kahvaltı);
        butun_kategoriler_gida_sekerleme = findViewById(R.id.butun_kategoriler_gida_sekerleme);
        butun_kategoriler_icecek = findViewById(R.id.butun_kategoriler_icecek);
        butun_kategoriler_deterjan = findViewById(R.id.butun_kategoriler_deterjan);
        butun_kategoriler_bebek_oyuncak = findViewById(R.id.butun_kategoriler_bebek_oyuncak);

        Intent intent = getIntent();
        username_email = intent.getStringExtra("username_email");
        pass = intent.getStringExtra("pass");

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ButunKategoriler.super.onBackPressed(); //Sayfa yoksa uygulamadan çıkacak yada önceki activity'e dönecek

            }
        });



        butun_kategoriler_sut_kahvaltı.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), SutKahvalti.class);
                intent.putExtra("username_email", username_email);
                intent.putExtra("pass", pass);
                startActivity(intent);

            }
        });


        butun_kategoriler_gida_sekerleme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), GidaSekerleme.class);
                intent.putExtra("username_email", username_email);
                intent.putExtra("pass", pass);
                startActivity(intent);

            }
        });


        butun_kategoriler_icecek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), Icecek.class);
                intent.putExtra("username_email", username_email);
                intent.putExtra("pass", pass);
                startActivity(intent);

            }
        });


        butun_kategoriler_deterjan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), Deterjan.class);
                intent.putExtra("username_email", username_email);
                intent.putExtra("pass", pass);
                startActivity(intent);
            }
        });


        butun_kategoriler_bebek_oyuncak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), BebekOyuncak.class);
                intent.putExtra("username_email", username_email);
                intent.putExtra("pass", pass);
                startActivity(intent);

            }
        });


    }
}