package com.example.heinenj.heinenjocelinas;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;




public class Register{

    private int idu;
    private String login;
    private String password;
    private int scoreQuest;
    private int scoreMath;

    public Register() {

    }

    public Register(String login, String password) {
        this.setLogin(login);
        this.setPassword(password);
        this.scoreQuest=0;
        this.scoreMath=0;

    }

    public int getIdu() {
        return idu;
    }

    public void setIdu(int idu) {
        this.idu = idu;
    }


    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public int getscoreQuest() {
        return scoreQuest;
    }

    public int getScoreMath() {
        return scoreMath;
    }


    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {

        this.password = password;
    }

    public void setScoreQuest(int scoreQuest) {
        this.scoreQuest = scoreQuest;
    }

    public void setScoreMath(int scoreMath) {
        this.scoreMath = scoreMath;
    }

    @Override
    public String toString(){
        return this.login;
    }
}