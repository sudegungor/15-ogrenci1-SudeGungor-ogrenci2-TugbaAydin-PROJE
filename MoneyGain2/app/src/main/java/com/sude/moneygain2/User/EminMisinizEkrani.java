package com.sude.moneygain2.User;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.sude.moneygain2.Database.DatabaseHelper;
import com.sude.moneygain2.R;

public class EminMisinizEkrani extends AppCompatActivity {

    Button evet_btn, hayir_btn;
    String username_email, pass;

    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_emin_misiniz_ekrani);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int widht = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(widht*.8), (int)(height*.5));

        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.CENTER;
        params.x = 0;
        params.y = -20;

        getWindow().setAttributes(params);

        //*****************************************//


        db = new DatabaseHelper(this);

        Intent intent = getIntent();
        username_email = intent.getStringExtra("username_email");
        pass = intent.getStringExtra("pass");

        evet_btn = findViewById(R.id.evet_btn);
        hayir_btn = findViewById(R.id.hayir_btn);

        hayir_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Profil.class);
                intent.putExtra("username_email", username_email);
                intent.putExtra("pass", pass);
                startActivity(intent);
            }
        });


        evet_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                db.userDelete(username_email);

                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);

            }
        });

    }
}