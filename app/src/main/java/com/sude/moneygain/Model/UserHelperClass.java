package com.sude.moneygain.Model;

public class UserHelperClass {

    String ad, nick, email, telno;

    public UserHelperClass(String ad, String nick, String email, String telno){
        this.ad = ad;
        this.nick = nick;
        this.email = email;
        this.telno = telno;
    }

    public UserHelperClass(){

    }

    public String getAd() {
        return this.ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public String getNick() {
        return this.nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelno() {
        return telno;
    }

    public void setTelno(String telno) {
        this.telno = telno;
    }
}
