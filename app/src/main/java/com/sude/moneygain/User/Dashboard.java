package com.sude.moneygain.User;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.sude.moneygain.Adapter.RecUrunlerAdapter;
import com.sude.moneygain.Adapter.SearcAdapter;
import com.sude.moneygain.Database.DatabaseHelper;
import com.sude.moneygain.Model.CategoriesHelperClass;
import com.sude.moneygain.Model.RecMarketlerHelperClasses;
import com.sude.moneygain.Adapter.KategoriAdapter;
import com.sude.moneygain.Model.RecUrunlerHelperClasses;
import com.sude.moneygain.Model.UserHelperClass;
import com.sude.moneygain.R;
import com.sude.moneygain.Adapter.RecMarketlerAdapter;
import com.sude.moneygain.User.Kategoriler.BebekOyuncak;
import com.sude.moneygain.User.Kategoriler.Deterjan;
import com.sude.moneygain.User.Kategoriler.GidaSekerleme;
import com.sude.moneygain.User.Kategoriler.Icecek;
import com.sude.moneygain.User.Kategoriler.SutKahvalti;

import java.util.ArrayList;

public class Dashboard extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    static final float END_SCALE = 0.7f;

    static DatabaseHelper db;

    String username_email, pass;

    EditText search_edit_text;
    TextView kategori_hepsinigoruntule;
    ImageView menuIcon;

    LinearLayout contentView, profilim_linearLayout, biz_kimiz, kuponlar_linearLayout;
    RecyclerView rec_marketler, enCokGoruntulenenleRecycler, kategorileRecycler, search_recycler;
    DrawerLayout drawerLayout;
    NavigationView navigationView;

    RecyclerView.Adapter rec_adapter;

    private GradientDrawable gradient1, gradient2, gradient3, gradient4, gradient5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_dashboard);


        //Intent ile verileri aldık
        Intent intent1 = getIntent();
        username_email = intent1.getStringExtra("username_email");
        pass = intent1.getStringExtra("pass");


        rec_marketler = findViewById(R.id.rec_marketler);
        enCokGoruntulenenleRecycler = findViewById(R.id.en_cok_goruntulenenler_recycler);
        kategorileRecycler = findViewById(R.id.kategoriler_recycler);
        search_recycler = findViewById(R.id.search_recycler);
        kategori_hepsinigoruntule = findViewById(R.id.kategori_hepsinigoruntule);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);
        menuIcon = findViewById(R.id.menu_icon);
        contentView = findViewById(R.id.contentView);
        search_edit_text = findViewById(R.id.search_edit_text);
        profilim_linearLayout = findViewById(R.id.profilim_linearLayout);
        biz_kimiz = findViewById(R.id.biz_kimiz);
        kuponlar_linearLayout = findViewById(R.id.kuponlar_linearLayout);



        db = new DatabaseHelper(this);


        kuponlar_linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Kuponlar.class);
                intent.putExtra("username_email", username_email);
                intent.putExtra("pass", pass);
                startActivity(intent);
            }
        });


        biz_kimiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), BizKimiz.class);
                v.getContext().startActivity(intent);
            }
        });

        profilim_linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(v.getContext(), Profil.class);
                intent.putExtra("username_email", username_email);
                intent.putExtra("pass", pass);
                v.getContext().startActivity(intent);

            }
        });


        kategori_hepsinigoruntule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ButunKategoriler.class);
                v.getContext().startActivity(intent);
            }
        });

        search_edit_text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {


            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                String search_nick = String.valueOf(search_edit_text.getText());
                searchRecycler(search_nick);

            }

            @Override
            public void afterTextChanged(Editable s) {



            }
        });


        navigationDrawer();

        rec_marketler();

        enCokGoruntulenenleRecycler();

        kategorileRecycler();

    }


    private void navigationDrawer() {

        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);

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


    private void kategorileRecycler() {


        gradient1 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{0xff7adccf, 0xff7adccf});
        gradient2 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{0xffd4cbe5, 0xffd4cbe5});
        gradient3 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{0xfff7c59f, 0xFFf7c59f});
        gradient4 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{0xffb8d7f5, 0xffb8d7f5});
        gradient5 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{0xFFFFD7D7, 0xFFFFD7D7});


        ArrayList<CategoriesHelperClass> categoriesHelperClass = new ArrayList<>();
        categoriesHelperClass.add(new CategoriesHelperClass(gradient2, R.drawable.milk, "Süt, Kahvaltılık", username_email, pass));
        categoriesHelperClass.add(new CategoriesHelperClass(gradient3, R.drawable.candy, "Gıda, Şekerleme", username_email, pass));
        categoriesHelperClass.add(new CategoriesHelperClass(gradient4, R.drawable.drink, "         İçecek", username_email, pass));
        categoriesHelperClass.add(new CategoriesHelperClass(gradient5, R.drawable.cleaning, "Deterjan, Temizlik", username_email, pass));
        categoriesHelperClass.add(new CategoriesHelperClass(gradient1, R.drawable.baby, "Bebek, Oyuncak", username_email, pass));


        kategorileRecycler.setHasFixedSize(true);
        rec_adapter = new KategoriAdapter(categoriesHelperClass);
        kategorileRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        kategorileRecycler.setAdapter(rec_adapter);

    }


    private void enCokGoruntulenenleRecycler() {


        enCokGoruntulenenleRecycler.setHasFixedSize(true);
        enCokGoruntulenenleRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        ArrayList<RecUrunlerHelperClasses> mostViewedLocations = new ArrayList<>();
        mostViewedLocations.add(new RecUrunlerHelperClasses(R.drawable.cola_2_5, "Coca Cola 2,5L", R.drawable.eti_lifalif_yulaf_ezmesi_500g, "Eti Lifalif Yulaf Ezmesi 500 g"));
        mostViewedLocations.add(new RecUrunlerHelperClasses(R.drawable.nescafe_3u1_arada_sade, "Nescafe 3ü1 Arada Sade", R.drawable.marmara_birlik_kuru_sele_zeytin_400_g, "Marmara Birlik Kuru Sele Zeytin 400 g Pet"));

        rec_adapter = new RecUrunlerAdapter(mostViewedLocations);
        enCokGoruntulenenleRecycler.setAdapter(rec_adapter);
    }


    private void rec_marketler() {

        rec_marketler.setHasFixedSize(true);
        rec_marketler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        ArrayList<RecMarketlerHelperClasses> recMarketlerLocations = new ArrayList<>();

        recMarketlerLocations.add(new RecMarketlerHelperClasses(R.drawable.carrefour_pic, "CarrefourSA", "Güçlü bir finansal pozisyona sahip olan CarrefourSA, büyüme ve değer yaratma potansiyeli olan tüm projeleri kendi kaynaklarıyla değerlendirerek yatırımlarına devam etmeyi, bu sayede tüketiciler için yakın, keyifli ve değer yaratan alışverişin adresi olmayı hedeflemektedir."));
        recMarketlerLocations.add(new RecMarketlerHelperClasses(R.drawable.a101_pic, "A-101", "A101 Yeni Mağazacılık A.Ş., Türkiye'de indirim marketi pazarına 28 Mart 2008 tarihinde 121 mağaza ile giriş yapan perakende marketler zinciri."));
        recMarketlerLocations.add(new RecMarketlerHelperClasses(R.drawable.happycenter_pic, "Happy Center", "Happy Center süpermarket formatında tüketiciye doyumsuz bir alışveriş yapmak amacıyla çeşit ve kalite dengelemesiyle tüketicilerimizin hizmetindedir."));


        rec_adapter = new RecMarketlerAdapter(recMarketlerLocations);

        rec_marketler.setAdapter(rec_adapter);

    }


    private void searchRecycler(String nick) {

        UserHelperClass user = new UserHelperClass();

        search_recycler.setHasFixedSize(true);
        search_recycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        ArrayList<UserHelperClass> recSearchLocations = new ArrayList<>();


        user = db.getNickileInfo(nick);

        if (user.getNick() != null) {
            recSearchLocations.add(new UserHelperClass(user.getAd(), user.getNick(), user.getEmail(), user.getTelno()));
        }

        else{
            recSearchLocations.clear();
            rec_adapter.notifyDataSetChanged();
        }

        rec_adapter = new SearcAdapter(recSearchLocations);
        search_recycler.setAdapter(rec_adapter);


    }


}