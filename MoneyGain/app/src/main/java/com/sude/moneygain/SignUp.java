package com.sude.moneygain;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

import java.sql.SQLOutput;
import java.util.regex.Pattern;

public class SignUp extends AppCompatActivity {

    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    //"(?=.*[0-9])" +         //at least 1 digit
                    //"(?=.*[a-z])" +         //at least 1 lower case letter
                    //"(?=.*[A-Z])" +         //at least 1 upper case letter
                    "(?=.*[a-zA-Z])" +      //any letter
                    "(?=.*[@#$%^&+=])" +    //at least 1 special character
                    "(?=\\S+$)" +           //no white spaces
                    ".{4,}" +               //at least 4 characters
                    "$");


    DatabaseHelper db;

    Button button_kayit, button_hesapvar;
    TextInputLayout text_name, text_nickname, text_email, text_phoneno, text_pass, text_pass2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_sign_up);

        db = new DatabaseHelper(this);

        button_kayit = findViewById(R.id.button_kayit);
        button_hesapvar = findViewById(R.id.button_hesapvar);
        text_name = findViewById(R.id.name);
        text_nickname = findViewById(R.id.text_nick);
        text_email = findViewById(R.id.email);
        text_phoneno = findViewById(R.id.phoneNo);
        text_pass = findViewById(R.id.password);
        text_pass2 = findViewById(R.id.password2);


        button_kayit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String s1 = text_name.getEditText().getText().toString();
                String s2 = text_nickname.getEditText().getText().toString();
                String s3 = text_email.getEditText().getText().toString();
                String s4 = text_phoneno.getEditText().getText().toString();
                String s5 = text_pass.getEditText().getText().toString();
                String s6 = text_pass2.getEditText().getText().toString();




                if(s1.equals("")){
                    text_name.setError("Field can't be empty");

                }

                else if (s2.equals("")){
                    text_nickname.setError("Field can't be empty");

                }

                else if (s4.equals("")){
                    text_phoneno.setError("Field can't be empty");

                }

                else if (s5.equals("")){
                    text_pass.setError("Field can't be empty");

                }

                else if (s6.equals("")){
                    text_pass2.setError("Field can't be empty");
                }

                else if(!validateEmail()){
                    System.out.println("hatalı");
                }

                else{
                    if(s5.equals(s6)){

                        Boolean chknick = db.checkNick(s2);
                        Boolean chkemail = db.checkEmail(s3);

                        if(chknick == true ){

                            if(chkemail == true) {

                                Boolean insert = db.insert(s1, s2, s3, s4, s5);

                                if (insert == true) {

                                    text_name.getEditText().setText("");
                                    text_nickname.getEditText().setText("");
                                    text_email.getEditText().setText("");
                                    text_phoneno.getEditText().setText("");
                                    text_pass.getEditText().setText("");
                                    text_pass2.getEditText().setText("");
                                    text_name.setError("");
                                    text_nickname.setError("");
                                    text_email.setError("");
                                    text_phoneno.setError("");
                                    text_pass.setError("");
                                    text_pass2.setError("");


                                }
                            }

                            else{
                                text_email.setError("Bu e-posta adresiyle kayıtlı zaten bir hesap var");
                            }

                        }

                        else{
                            text_nickname.setError("Bu kullanıcı adıyla kayıtlı zaten bir hesap var");
                        }


                    }

                    else{
                        text_pass2.setError("Şifreler uyuşmuyor");
                    }

                }
            }
        });


        button_hesapvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(SignUp.this, Login.class);
                startActivity(intent);
            }
        });






    }



    private boolean validateEmail() {

        String emailInput = text_email.getEditText().getText().toString().trim();

        if (emailInput.isEmpty()) {
            text_email.setError("Field can't be empty");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
            text_email.setError("Please enter a valid email address");
            return false;
        } else {
            text_email.setError(null);
            return true;
        }
    }




}