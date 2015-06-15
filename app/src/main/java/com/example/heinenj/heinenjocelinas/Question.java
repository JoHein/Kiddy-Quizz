package com.example.heinenj.heinenjocelinas;

import android.provider.ContactsContract;

/**
 * Created by fbm on 06/03/2015.
 */
public class Question {

    private int id;
    private String question;
    private String bonneReponse;
    private String mauvaiseReponse1;
    private String mauvaiseReponse2;
    private String category;

    public Question() {

    }

    public Question(String question, String bonneReponse, String mauvaiseReponse1, String mauvaiseReponse2, String category) {
        this.setQuestion(question);
        this.setBonneReponse(bonneReponse);
        this.setMauvaiseReponse1(mauvaiseReponse1);
        this.setMauvaiseReponse2(mauvaiseReponse2);
        this.setCategory(category);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public String getBonneReponse() {
        return bonneReponse;
    }

    public String getMauvaiseReponse1() {
        return mauvaiseReponse1;
    }

    public String getMauvaiseReponse2() {
        return mauvaiseReponse2;
    }

    public String getCategory() {
        return category;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setBonneReponse(String bonneReponse) {

        this.bonneReponse = bonneReponse;
    }

    public void setMauvaiseReponse1(String mauvaiseReponse1) {
        this.mauvaiseReponse1 = mauvaiseReponse1;
    }

    public void setMauvaiseReponse2(String mauvaiseReponse2) {
        this.mauvaiseReponse2 = mauvaiseReponse2;
    }

    public void setCategory(String category){
        this.category = category;
    }
}
