package com.sude.moneygain;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String VERITABANI = "Deneme";

    public static final int SURUM = 3;


    public DatabaseHelper(Context context) {
        super(context, VERITABANI, null, SURUM);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE user (email Text PRIMARY KEY, password Text, ad Text, nick Text, phone_no Text);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS user");
        onCreate(db);

    }

    public boolean insert(String ad, String nick, String email, String phone_no, String password){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email", email);
        contentValues.put("password", password);
        contentValues.put("ad", ad);
        contentValues.put("nick", nick);
        contentValues.put("phone_no", phone_no);

        long ins = db.insert("user",null,contentValues);

        if(ins==-1)
            return  false;

        else
            return true;

    }


    public Boolean checkNick(String nick){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from user where nick=? " ,new String[]{nick});

        if (cursor.getCount()>0){
            return false;
        }

        else
            return true;
    }

    public Boolean checkEmail(String email){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from user where email=? " ,new String[]{email});

        if (cursor.getCount()>0){
            return false;
        }

        else
            return true;
    }

    public Boolean checkUserEmail(String email,String password){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from user where email=? and password=?",new String[]{email,password});

        if (cursor.getCount()>0){
            return true;
        }

        else
            return false;
    }

    public Boolean checkUserNick(String nick,String password){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from user where nick=? and password=?",new String[]{nick,password});

        if (cursor.getCount()>0){
            return true;
        }

        else
            return false;
    }


    public ArrayList<String> getInfo(String email, String password){

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from user where email=? and password=?",new String[]{email,password});

        ArrayList<String> arrayList = new ArrayList();

        while(cursor.moveToNext()){

            arrayList.add(cursor.getString(0));
            arrayList.add(cursor.getString(1));
            arrayList.add(cursor.getString(2));
            arrayList.add(cursor.getString(3));
            arrayList.add(cursor.getString(4));
            arrayList.add(cursor.getString(5));
            arrayList.add(cursor.getString(6));

        }

        if (arrayList == null){
            db.close();;
            System.out.println("BOŞ");
            return null;
        }

        else{
            db.close();
            return arrayList;
        }
    }




}
