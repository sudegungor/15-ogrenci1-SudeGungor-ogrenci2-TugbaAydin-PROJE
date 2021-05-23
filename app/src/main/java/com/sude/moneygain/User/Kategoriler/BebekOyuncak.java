package com.sude.moneygain.User.Kategoriler;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.android.material.navigation.NavigationView;
import com.sude.moneygain.Adapter.RecUrunlerAdapter;
import com.sude.moneygain.Model.RecUrunlerHelperClasses;
import com.sude.moneygain.R;
import com.sude.moneygain.User.ButunKategoriler;
import com.sude.moneygain.User.Dashboard;
import com.sude.moneygain.User.Login;
import com.sude.moneygain.User.Profil;

import java.util.ArrayList;

public class BebekOyuncak extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener  {

    private static final float END_SCALE = 0.7f;

    RecyclerView rec_urunler1;
    RecyclerView.Adapter adapter;

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ImageView menuIcon;
    LinearLayout contentView;
    String username_email, pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_bebek_oyuncak);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);
        menuIcon = findViewById(R.id.menu_icon);
        contentView = findViewById(R.id.contentView);

        Intent intent8 = getIntent();
        username_email = intent8.getStringExtra("username_email");
        pass = intent8.getStringExtra("pass");

        rec_urunler1 = findViewById(R.id.rec_urunler1);
        rec_urunler1();
        navigationDrawer();
    }

    private void navigationDrawer() {

        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_Bebek);

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

    private void rec_urunler1(){

        rec_urunler1.setHasFixedSize(true);
        rec_urunler1.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));

        ArrayList<RecUrunlerHelperClasses> recUrunlerHelperClasses = new ArrayList<>();

        recUrunlerHelperClasses.add(new RecUrunlerHelperClasses(R.drawable.bebelac_gold_sutlu_balli_irmikli, "Bebelac Gold Sütlü Ballı İrmikli Kaşık Maması 250 g 6-36 Ay", R.drawable.sleepy_natural_maxi, "Sleepy Natural 4 Beden 30'lu Cocuk Bezi"));

        adapter = new RecUrunlerAdapter(recUrunlerHelperClasses);

        rec_urunler1.setAdapter(adapter);

    }
}