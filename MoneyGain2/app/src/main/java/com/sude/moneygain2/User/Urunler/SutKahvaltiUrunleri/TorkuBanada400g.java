package com.sude.moneygain2.User.Urunler.SutKahvaltiUrunleri;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.sude.moneygain2.R;
import com.sude.moneygain2.User.DetayliEkran;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class TorkuBanada400g extends Activity {


    static TextView textView_isim;
    static TextView textView_fiyat;

    static ImageView market_logo;

    Button detay_btn;

    public ArrayList liste_fiyat = new ArrayList();
    public ArrayList liste_isim = new ArrayList();

    public static ArrayList<Float> liste_float = new ArrayList();
    private ProgressDialog progressDialog;
    private static String URL1 ="https://www.happycenter.com.tr/Torku_Banada_400_Gr";
    private static String URL2 ="https://www.a101.com.tr/market/torku-banada-kakaolu-findik-kremasi-400-g/";
    private static String URL3 ="https://www.carrefoursa.com/torku-banada-400-g-p-30084655";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.urun_popup_dizayn);

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


        market_logo = findViewById(R.id.market_logo);

        textView_isim = (TextView) findViewById(R.id.textView_isim);
        textView_fiyat = (TextView) findViewById(R.id.textView_fiyat);
        detay_btn = (Button) findViewById(R.id.detay_btn);

        new HCVeriGetir().execute();


    }


    public static void karsilastir(ArrayList<String> liste1, ArrayList<String> liste2){



        for (int i = 0; i < liste1.size(); i++) {

            String deger = liste1.get(i);
            deger = deger.replaceAll(",", ".");
            deger = deger.replaceAll(" ", "");
            liste_float.add(Float.parseFloat(deger));

        }

        float en_kucuk = liste_float.get(0);
        String en_kucuk_isim = liste2.get(0);


        for (int i = 1; i < liste_float.size(); i++) {
            if (en_kucuk > liste_float.get(i)) {
                en_kucuk = liste_float.get(i);
                en_kucuk_isim = liste2.get(i);


            }
        }

        textView_fiyat.setText("En Uygun Fiyat: " + String.valueOf(en_kucuk) + " TL");
        textView_isim.setText(en_kucuk_isim);

        if(textView_isim.getText().equals("Torku Banada 400 g || HappyCenter"))
            market_logo.setImageResource(R.drawable.happycenter_pic);

        else if(textView_isim.getText().equals("Torku Banada 400 g || A101"))
            market_logo.setImageResource(R.drawable.a101_pic);

        else if(textView_isim.getText().equals("Torku Banada 400 g || CarrefourSA"))
            market_logo.setImageResource(R.drawable.carrefour_pic);

    }

    private class HCVeriGetir extends AsyncTask<Void, Void, Void> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }



        @Override
        protected Void doInBackground(Void... voids) {

            try {
                Document doc = Jsoup.connect(URL1).timeout(30*1000).get();

                Elements fiyat = doc.select("h2[id='fiyat']");
                liste_fiyat.add(fiyat.text().substring(0, fiyat.text().length()-1));

                liste_isim.add("Torku Banada 400 g || HappyCenter");

            } catch (IOException e) {
                e.printStackTrace();
            }


            return null;
        }


        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            new A101VeriGetir().execute();

        }
    }


    private class A101VeriGetir extends AsyncTask<Void, Void, Void>{


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }



        @Override
        protected Void doInBackground(Void... voids) {

            try {
                Document doc = Jsoup.connect(URL2).timeout(30*1000).get();

                Elements fiyat = doc.select("div[class='price single']");

                if (fiyat.text().length()==0) {
                    fiyat = doc.select("div[class='price new']");
                }

                liste_fiyat.add(fiyat.text());

                liste_isim.add("Torku Banada 400 g || A101");


            } catch (IOException e) {
                e.printStackTrace();
            }


            return null;
        }


        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            new CarrefourVeriGetir().execute();

        }
    }


    private class CarrefourVeriGetir extends AsyncTask<Void, Void, Void>{


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(TorkuBanada400g.this);
            progressDialog.setTitle("Ürünler ve Fiyatlar");
            progressDialog.setMessage("Lütfen Bekleyiniz");
            progressDialog.setIndeterminate(false);
            progressDialog.show();
        }



        @Override
        protected Void doInBackground(Void... voids) {


            try {
                Document doc = Jsoup.connect(URL3).timeout(30*1000).get();

                Elements fiyat = doc.select("span[itemprop='price']");

                if (fiyat.text().length()==0) {
                    fiyat = doc.select("div[class='item-price js-variant-discounted-price']");
                }

                liste_fiyat.add(fiyat.text().substring(0, fiyat.text().length()-3));

                liste_isim.add("Torku Banada 400 g || CarrefourSA");

            } catch (IOException e) {
                e.printStackTrace();
            }


            return null;
        }


        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);


            karsilastir(liste_fiyat,liste_isim);


            progressDialog.dismiss();


            detay_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(v.getContext(), DetayliEkran.class);
                    intent.putExtra("liste_fiyat", liste_fiyat); //veri gönderiliyor
                    intent.putExtra("liste_isim", liste_isim); //veri gönderiliyor
                    intent.putExtra("urun_adi", "torku_banada_400_g"); //veri gönderiliyor
                    intent.putExtra("URL3", URL3); //veri gönderiliyor
                    startActivity(intent);


                }
            });

        }
    }



}