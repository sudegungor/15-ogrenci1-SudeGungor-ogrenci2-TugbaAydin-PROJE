package com.sude.moneygain.User;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.textfield.TextInputLayout;
import com.sude.moneygain.Database.DatabaseHelper;
import com.sude.moneygain.R;
import com.sude.moneygain.User.Kategoriler.BebekOyuncak;
import com.sude.moneygain.User.Kategoriler.Deterjan;
import com.sude.moneygain.User.Kategoriler.GidaSekerleme;
import com.sude.moneygain.User.Kategoriler.Icecek;
import com.sude.moneygain.User.Kategoriler.SutKahvalti;

import java.util.ArrayList;

public class Profil extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{


    private static final float END_SCALE = 0.7f;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ImageView menuIcon;
    LinearLayout contentView;
    String username_email, pass;

    TextView full_name,yukari_nick;

    RelativeLayout kupon_relativelayout;

    DatabaseHelper db;

    ArrayList<String> arrayList ;

    TextInputLayout profil_isim,profil_nick,profil_mail,profil_telno,profil_pass;

    Button guncelle_btn,kaydet_btn, sil_btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_profil);

        db=new DatabaseHelper(this);

        arrayList = new ArrayList();

        Intent intent = getIntent();
        username_email = intent.getStringExtra("username_email");
        pass = intent.getStringExtra("pass");


        profil_isim = findViewById(R.id.profil_isim);
        profil_nick = findViewById(R.id.profil_nick);
        profil_mail = findViewById(R.id.profil_mail);
        profil_telno = findViewById(R.id.profil_telno);
        profil_pass = findViewById(R.id.profil_pass);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);
        menuIcon = findViewById(R.id.menu_icon);
        contentView = findViewById(R.id.contentView);
        guncelle_btn = findViewById(R.id.guncelle_btn);
        kaydet_btn = findViewById(R.id.kaydet_btn);

        full_name = findViewById(R.id.full_name);
        yukari_nick = findViewById(R.id.yukari_nick);

        kupon_relativelayout = findViewById(R.id.kupon_relativelayout);
        sil_btn = findViewById(R.id.sil_btn);


        arrayList = db.getInfo(username_email,pass);


        full_name.setText(arrayList.get(2));
        yukari_nick.setText(arrayList.get(3));

        profil_mail.getEditText().setText(arrayList.get(0));
        profil_pass.getEditText().setText(arrayList.get(1));
        profil_isim.getEditText().setText(arrayList.get(2));
        profil_nick.getEditText().setText(arrayList.get(3));
        profil_telno.getEditText().setText(arrayList.get(4));

        profil_mail.setEnabled(false);
        profil_pass.setEnabled(false);
        profil_isim.setEnabled(false);
        profil_nick.setEnabled(false);
        profil_telno.setEnabled(false);


        kaydet_btn.setVisibility(View.GONE);


        navigationDrawer();


        sil_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), EminMisinizEkrani.class);
                intent.putExtra("username_email", username_email);
                intent.putExtra("pass", pass);
                startActivity(intent);
            }
        });


        guncelle_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                kaydet_btn.setVisibility(View.VISIBLE);
                guncelle_btn.setVisibility(View.GONE);


                profil_pass.setEnabled(true);
                profil_isim.setEnabled(true);
                profil_nick.setEnabled(true);
                profil_telno.setEnabled(true);

                kaydet_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String string_mail = String.valueOf(profil_mail.getEditText().getText());
                        String degistirilmiş_pass = String.valueOf(profil_pass.getEditText().getText());
                        String degistirilmiş_isim = String.valueOf(profil_isim.getEditText().getText());
                        String degistirilmiş_nick = String.valueOf(profil_nick.getEditText().getText());
                        String degistirilmiş_telno = String.valueOf(profil_telno.getEditText().getText());


                        db.kaydiGuncelle(degistirilmiş_isim,degistirilmiş_nick,string_mail,degistirilmiş_telno,degistirilmiş_pass);


                        profil_pass.setEnabled(false);
                        profil_isim.setEnabled(false);
                        profil_nick.setEnabled(false);
                        profil_telno.setEnabled(false);

                        kaydet_btn.setVisibility(View.GONE);
                        guncelle_btn.setVisibility(View.VISIBLE);


                    }
                });




            }
        });

        kupon_relativelayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), Kuponlar.class);
                intent.putExtra("username_email", username_email);
                intent.putExtra("pass", pass);
                startActivity(intent);

            }
        });




    }


    private void navigationDrawer() {

        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_profile);

        menuIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(drawerLayout.isDrawerVisible(GravityCompat.START))
                    drawerLayout.closeDrawer(GravityCompat.START);

                else
                    drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        animateNavigationDrawer();
    }


    //Menü açılırken kaydırırken efektli olması için


    private void animateNavigationDrawer() {

        drawerLayout.setScrimColor(0xFFFFD7D7);

        drawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {

                final float diffScaledOffset = slideOffset * (1 - END_SCALE);
                final float offsetScale = 1- diffScaledOffset;

                contentView.setScaleX(offsetScale);
                contentView.setScaleY(offsetScale);

                final float xOffset = drawerView.getWidth() * slideOffset;
                final float xOffsetDiff = contentView.getWidth() * diffScaledOffset /2 ;
                final float xTranslation = xOffset - xOffsetDiff;
                contentView.setTranslationX(xTranslation);

            }

            @Override
            public void onDrawerOpened(@NonNull View drawerView) {

            }

            @Override
            public void onDrawerClosed(@NonNull View drawerView) {

            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });
    }

    @Override
    public void onBackPressed() {

        if(drawerLayout.isDrawerVisible(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else
            super.onBackPressed();
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){

            case R.id.nav_home:
                Intent intent = new Intent(getApplicationContext(), Dashboard.class);
                intent.putExtra("username_email", username_email);
                intent.putExtra("pass", pass);
                startActivity(intent);
                break;

            case R.id.nav_butunKategoriler:
                Intent intent2 = new Intent(getApplicationContext(), ButunKategoriler.class);
                intent2.putExtra("username_email", username_email);
                intent2.putExtra("pass", pass);
                startActivity(intent2);
                break;

            case R.id.nav_profile:

                Intent intent3 = new Intent(getApplicationContext(), Profil.class);
                intent3.putExtra("username_email", username_email);
                intent3.putExtra("pass", pass);
                startActivity(intent3);

                break;

            case R.id.nav_logout:
                Intent intent4 = new Intent(getApplicationContext(), Login.class);
                intent4.putExtra("username_email", username_email);
                intent4.putExtra("pass", pass);
                startActivity(intent4);
                break;



            case R.id.nav_Sut:

                Intent intent6 = new Intent(getApplicationContext(), SutKahvalti.class);
                intent6.putExtra("username_email", username_email);
                intent6.putExtra("pass", pass);
                startActivity(intent6);
                break;

            case R.id.nav_Gida:

                Intent intent7 = new Intent(getApplicationContext(), GidaSekerleme.class);
                intent7.putExtra("username_email", username_email);
                intent7.putExtra("pass", pass);
                startActivity(intent7);
                break;


            case R.id.nav_Icecek:

                Intent intent8 = new Intent(getApplicationContext(), Icecek.class);
                intent8.putExtra("username_email", username_email);
                intent8.putExtra("pass", pass);
                startActivity(intent8);
                break;


            case R.id.nav_Temizlik:

                Intent intent9 = new Intent(getApplicationContext(), Deterjan.class);
                intent9.putExtra("username_email", username_email);
                intent9.putExtra("pass", pass);
                startActivity(intent9);
                break;


            case R.id.nav_Bebek:

                Intent intent10 = new Intent(getApplicationContext(), BebekOyuncak.class);
                intent10.putExtra("username_email", username_email);
                intent10.putExtra("pass", pass);
                startActivity(intent10);
                break;


        }

        return true;
    }


}