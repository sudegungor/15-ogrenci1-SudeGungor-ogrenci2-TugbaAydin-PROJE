package com.sude.moneygain2.User;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.sude.moneygain2.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;


public class DetayliEkran extends AppCompatActivity {

    ImageView backBtn,urun_img;


    static TextView market_adi1, market_adi2, market_adi3,fiyati1,fiyati2,fiyati3;

    static EditText aciklama_text;

    static String acikalama2;

    public ArrayList liste_fiyat = new ArrayList();
    public ArrayList liste_isim = new ArrayList();

    static String urun_adi;


    private static String URL3 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_detayli_ekran);

        backBtn =findViewById(R.id.backBtn);
        urun_img =findViewById(R.id.urun_img);

        aciklama_text = (EditText) findViewById(R.id.aciklama_text);
        market_adi1 = findViewById(R.id.market_adi1);
        market_adi2 = findViewById(R.id.market_adi2);
        market_adi3 = findViewById(R.id.market_adi3);
        fiyati1 = findViewById(R.id.fiyati1);
        fiyati2 = findViewById(R.id.fiyati2);
        fiyati3 = findViewById(R.id.fiyati3);

        aciklama_text.setEnabled(false);

        Intent intent = getIntent();
        liste_fiyat = intent.getStringArrayListExtra("liste_fiyat");
        liste_isim = intent.getStringArrayListExtra("liste_isim");
        urun_adi = intent.getStringExtra("urun_adi");
        URL3 = intent.getStringExtra("URL3");

        bilgileri_ekle();

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DetayliEkran.super.onBackPressed(); //Sayfa yoksa uygulamadan çıkacak yada önceki activity'e dönecek

            }
        });

        new UrunBilgisi().execute();




    }

    public void bilgileri_ekle(){

        //Resim

        if(urun_adi.equals("danonesutkakaolu180ml"))
            urun_img.setImageResource(R.drawable.danone_sut_kakaolu_180_ml);

        else if(urun_adi.equals("goldKremfistikSekerIlavesizYerFistigiEzmesi340g"))
            urun_img.setImageResource(R.drawable.gold_kremfistik_seker_ilavesiz_yer_fistigi_ezmesi_340_g);

        else if(urun_adi.equals("NestleMısırGevreğiÇikolatalı310G"))
            urun_img.setImageResource(R.drawable.nestle_misir_gevregi_cikolatali_310g);

        else if(urun_adi.equals("EtiLifalifYulafEzmesi500g"))
            urun_img.setImageResource(R.drawable.eti_lifalif_yulaf_ezmesi_500g);

        else if(urun_adi.equals("torku_banada_400_g"))
            urun_img.setImageResource(R.drawable.torku_banada_400_g);

        else if(urun_adi.equals("pinar_protein_laktozsuz_kakaolu_sut_500_ml"))
            urun_img.setImageResource(R.drawable.pinar_protein_laktozsuz_kakaolu_sut_500_ml);

        else if(urun_adi.equals("anavarza_cicek_bali_850g"))
            urun_img.setImageResource(R.drawable.anavarza_cicek_bali_850g);

        else if(urun_adi.equals("marmara_birlik_kuru_sele_zeytin_400_g"))
            urun_img.setImageResource(R.drawable.marmara_birlik_kuru_sele_zeytin_400_g);

        else if(urun_adi.equals("cocaCola2_5l"))
            urun_img.setImageResource(R.drawable.cola_2_5);

        else if(urun_adi.equals("CaykurRizeTuristCay500g"))
            urun_img.setImageResource(R.drawable.caykur_rize_turist_cay_500_gr);

        else if(urun_adi.equals("PoweradeSporcuIcecegi500Ml"))
            urun_img.setImageResource(R.drawable.powerade_ice_blast_500_ml);

        else if(urun_adi.equals("CappyMeyveSuyuVisne1lt"))
            urun_img.setImageResource(R.drawable.cappy_meyve_suyu_visne_1_lt);

        else if(urun_adi.equals("Nescafe3u1AradaSade"))
            urun_img.setImageResource(R.drawable.nescafe_3u1_arada_sade);

        else if(urun_adi.equals("Nescafe2si1Arada"))
            urun_img.setImageResource(R.drawable.nescafe_2si1);

        else if(urun_adi.equals("DogusIhlamur20li"))
            urun_img.setImageResource(R.drawable.dogus_ihlamur_20li);

        else if(urun_adi.equals("FrukoGazoz"))
            urun_img.setImageResource(R.drawable.fruko_gazoz);

        else if(urun_adi.equals("LaysYogurtMevsimYesillikleriPatatesCipsi"))
            urun_img.setImageResource(R.drawable.lays_yogurt_mevsim_yesillikleri_cips);



        //Bilgiler

        fiyati1.setText(liste_fiyat.get(0).toString()+ " TL");
        fiyati2.setText(liste_fiyat.get(1).toString() + " TL");
        fiyati3.setText(liste_fiyat.get(2).toString() + " TL");
        market_adi1.setText(liste_isim.get(0).toString());
        market_adi2.setText(liste_isim.get(1).toString());
        market_adi3.setText(liste_isim.get(2).toString());


    }



    private class UrunBilgisi extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }


        @Override
        protected Void doInBackground(Void... voids) {


            try {
                Document doc = Jsoup.connect(URL3).timeout(30*1000).get();

                Elements aciklama = doc.select("div[class='product-details-tab tab-details']");
                acikalama2 = aciklama.text().substring(0,aciklama.text().length()-19);

            } catch (IOException e) {
                e.printStackTrace();
            }


            return null;
        }


        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            aciklama_text.setText(acikalama2);
        }
    }
}