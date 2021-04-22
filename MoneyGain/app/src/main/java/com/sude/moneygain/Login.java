package com.sude.moneygain;

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

public class Login extends AppCompatActivity {

    Button signUp;
    ImageView image;
    TextView logoText;
    TextView sloganText;
    TextInputLayout username;
    TextInputLayout password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        signUp = findViewById(R.id.signup_button);
        image = findViewById(R.id.logo_image);
        logoText = findViewById(R.id.logo_name);
        sloganText = findViewById(R.id.slogan_name);
        username = findViewById(R.id.username);
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





    }
}