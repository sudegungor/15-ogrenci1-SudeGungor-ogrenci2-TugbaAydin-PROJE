package com.sude.moneygain2.User;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.android.material.navigation.NavigationView;
import com.sude.moneygain2.Database.DatabaseHelper;
import com.sude.moneygain2.R;
import com.sude.moneygain2.User.Kategoriler.BebekOyuncak;
import com.sude.moneygain2.User.Kategoriler.Deterjan;
import com.sude.moneygain2.User.Kategoriler.GidaSekerleme;
import com.sude.moneygain2.User.Kategoriler.Icecek;
import com.sude.moneygain2.User.Kategoriler.SutKahvalti;

import java.util.ArrayList;

public class Kuponlar extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static final float END_SCALE = 0.7f;

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ImageView menuIcon;
    LinearLayout contentView;
    String username_email, pass;

    DatabaseHelper db;

    EditText text_kupon;

    ArrayList<String> kupon_arrayList ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_kuponlar);


        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);
        menuIcon = findViewById(R.id.menu_icon);
        contentView = findViewById(R.id.contentView);
        text_kupon = findViewById(R.id.text_kupon);

        navigationDrawer();


        //**********************************************************************//


        db = new DatabaseHelper(this);

        Intent intent = getIntent();
        username_email = intent.getStringExtra("username_email");
        pass = intent.getStringExtra("pass");


        kupon_arrayList = new ArrayList();

        kupon_arrayList = db.getKupon(username_email, pass);




        for (int i = 0; i < kupon_arrayList.size(); i++) {

            text_kupon.append(kupon_arrayList.get(i));

        }


    }


    private void navigationDrawer() {

        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_profile);

        menuIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (drawerLayout.isDrawerVisible(GravityCompat.START))
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
                final float offsetScale = 1 - diffScaledOffset;

                contentView.setScaleX(offsetScale);
                contentView.setScaleY(offsetScale);

                final float xOffset = drawerView.getWidth() * slideOffset;
                final float xOffsetDiff = contentView.getWidth() * diffScaledOffset / 2;
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

        if (drawerLayout.isDrawerVisible(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else
            super.onBackPressed();
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {

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