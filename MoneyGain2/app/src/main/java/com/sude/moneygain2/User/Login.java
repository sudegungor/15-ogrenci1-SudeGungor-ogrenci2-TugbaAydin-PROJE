package com.sude.moneygain2.User;

import androidx.appcompat.app.AppCompatActivity;


import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;
import com.sude.moneygain2.Database.DatabaseHelper;
import com.sude.moneygain2.R;


public class Login extends AppCompatActivity {

    private static final String TAG = "MoneyGain";
    private static final int RC_SIGN_IN = 9001;



    DatabaseHelper db;

    Button signUp,button_giris,button_google;
    ImageView image;
    TextView logoText,sloganText;
    TextInputLayout username,password;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);


        db = new DatabaseHelper(this);

        signUp = findViewById(R.id.signup_button);
        button_giris = findViewById(R.id.button_giris);
        image = findViewById(R.id.logo_image);
        logoText = findViewById(R.id.logo_name);
        sloganText = findViewById(R.id.slogan_name);
        username = findViewById(R.id.text_surname);
        password = findViewById(R.id.password);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, SignUp.class);

                Pair[] pairs = new Pair[7];
                pairs[0] = new Pair<View,String>(image,"logo_image");
                pairs[1] = new Pair<View,String>(image,"logo_text");
                pairs[2] = new Pair<View,String>(image,"logo_desc");
                pairs[3] = new Pair<View,String>(image,"username_tran");
                pairs[4] = new Pair<View,String>(image,"password_tran");
                pairs[5] = new Pair<View,String>(image,"button_tran");
                pairs[6] = new Pair<View,String>(image,"loginsignup_tran");

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(Login.this, pairs);
                    startActivity(intent,options.toBundle());
                }
            }
        });


        button_giris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username_email = username.getEditText().getText().toString();
                String pass = password.getEditText().getText().toString();


                if(username_email!=null && pass!= null){

                    Boolean checkUserEmail = db.checkUserEmail(username_email, pass);
                    Boolean checkUserNick = db.checkUserNick(username_email, pass);
                    System.out.println(checkUserEmail);
                    System.out.println(checkUserNick);

                    if(checkUserEmail == true || checkUserNick == true){

                        Intent intent = new Intent(Login.this, Dashboard.class);
                        intent.putExtra("username_email", username_email);
                        intent.putExtra("pass", pass);
                        startActivity(intent);
                    }

                    else{
                        password.setError("Kullanıcı adı veya şifre hatalı");
                    }
                }

            }
        });


    }






}