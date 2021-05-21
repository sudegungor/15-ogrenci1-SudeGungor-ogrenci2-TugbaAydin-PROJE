package com.sude.moneygain2.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Patterns;

import com.sude.moneygain2.Model.UserHelperClass;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^" +
                    "(?=.*[a-zA-Z])" +      //any letter
                    "(?=.*[@#$%^&+=])" +    //at least 1 special character
                    "(?=\\S+$)" +           //no white spaces
                    ".{4,}" +               //at least 4 characters
                    "$");

    public static final String VERITABANI = "Deneme";

    public static final int SURUM = 21;


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


    public boolean insert(String ad, String nick, String email, String phone_no, String password) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email", email);
        contentValues.put("password", password);
        contentValues.put("ad", ad);
        contentValues.put("nick", nick);
        contentValues.put("phone_no", phone_no);

        long ins = db.insert("user", null, contentValues);

        if (ins == -1)
            return false;

        else
            return true;

    }


    public UserHelperClass getNickileInfo(String nick) {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<UserHelperClass> arrayListUser = new ArrayList<>();

        UserHelperClass user = new UserHelperClass();

        Cursor cursor = db.rawQuery("Select ad, nick, email, phone_no from user where nick LIKE ?", new String[]{nick});

        cursor.moveToFirst();


        while (!cursor.isAfterLast()) {

            user.setAd(cursor.getString(cursor.getColumnIndex("ad")));
            user.setNick(cursor.getString(cursor.getColumnIndex("nick")));
            user.setEmail(cursor.getString(cursor.getColumnIndex("email")));
            user.setTelno(cursor.getString(cursor.getColumnIndex("phone_no")));


            cursor.moveToNext();
        }

        cursor.close();
        return user;
    }


    public Boolean checkNick(String nick) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from user where nick=? ", new String[]{nick});

        if (cursor.getCount() > 0) {
            return false;
        } else
            return true;
    }

    public Boolean checkEmail(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from user where email=? ", new String[]{email});

        if (cursor.getCount() > 0) {
            return false;
        } else
            return true;
    }

    public Boolean checkUserEmail(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from user where email=? and password=?", new String[]{email, password});


        if (cursor.getCount() > 0) {
            return true;
        } else
            return false;
    }

    public Boolean checkUserNick(String nick, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from user where nick=? and password=?", new String[]{nick, password});


        if (cursor.getCount() > 0) {
            return true;
        } else
            return false;
    }


    public ArrayList<String> getInfo(String username_email, String password) {

        SQLiteDatabase db = this.getReadableDatabase();

        ArrayList<String> arrayList = new ArrayList();

        String username_email_input = username_email.trim();

        if (!Patterns.EMAIL_ADDRESS.matcher(username_email_input).matches()) {
            Cursor cursor = db.rawQuery("select * from user where nick=? and password=?", new String[]{username_email, password});
            while (cursor.moveToNext()) {

                arrayList.add(cursor.getString(0));
                arrayList.add(cursor.getString(1));
                arrayList.add(cursor.getString(2));
                arrayList.add(cursor.getString(3));
                arrayList.add(cursor.getString(4));

            }

            if (arrayList == null) {
                System.out.println("BOŞ");
                return null;
            } else {
                db.close();
                return arrayList;
            }

        } else {

            Cursor cursor = db.rawQuery("select * from user where email=? and password=?", new String[]{username_email, password});
            while (cursor.moveToNext()) {

                arrayList.add(cursor.getString(0));
                arrayList.add(cursor.getString(1));
                arrayList.add(cursor.getString(2));
                arrayList.add(cursor.getString(3));
                arrayList.add(cursor.getString(4));
            }

            if (arrayList == null) {
                System.out.println("BOŞ");
                return null;
            } else {
                db.close();
                return arrayList;
            }
        }
    }


    public void kaydiGuncelle(String ad, String nick, String email, String phone_no, String password) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues bilgiGuncelle = new ContentValues();

        bilgiGuncelle.put("ad", ad);
        bilgiGuncelle.put("nick", nick);
        bilgiGuncelle.put("phone_no", phone_no);
        bilgiGuncelle.put("password", password);

        db.update("user", bilgiGuncelle, "email = ?", new String[]{email});

    }


}
