package com.example.heinenj.heinenjocelinas;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by heinenj on 03/06/15.
 */
public class RegisterDAO extends DAOBase {


    public static final String TABLE_USERS = "USERS_LOGIN";

    // Table: QUESTION
    public static final String COL_IDU = "idu";
    public static final String COL_LOGIN = "login";
    public static final String COL_PASSWORD = "password";
    public static final String COL_SCOREQUEST = "scoreQuest";
    public static final String COL_SCOREMATH = "scoreMath";

    //rajouter CATEGORY

    // retourne une chaîne de caractères représentant une instruction SQL de création de la table Question
    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_USERS + " (" +
                    COL_IDU + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COL_LOGIN + " TEXT NOT NULL, " +
                    COL_PASSWORD + " TEXT NOT NULL, " +
                    COL_SCOREQUEST + " INT, " +
                    COL_SCOREMATH + " INT);";


    public static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_USERS + ";";

    public RegisterDAO(Context context) {
        super(context);
    }

    public long insert(Register register) {

        // Création d'un ContentValues (fonctionne comme une HashMap)
        ContentValues values = new ContentValues();

        // Ajout clé/valeur : colonne/valeur

        values.put(COL_LOGIN, register.getLogin());
        values.put(COL_PASSWORD, register.getPassword());
        values.put(COL_SCOREQUEST, register.getscoreQuest());
        values.put(COL_SCOREMATH, register.getScoreMath());

        // Insertion de l'objet dans la BD via le ContentValues
        return getDB().insert(TABLE_USERS, null, values);
    }



    public int updateScoreQ(Register register) {

        //Récupère dans un Cursor les valeur correspondant à une question contenu dans la BD à l'aide de son id

        ContentValues values = new ContentValues();

        values.put(COL_SCOREQUEST, register.getscoreQuest());


        return getDB().update(TABLE_USERS, values, COL_IDU + " = '" + register.getIdu() + "'", null);

    }

    public int updateScoreMath(Register register) {

        //Récupère dans un Cursor les valeur correspondant à une question contenu dans la BD à l'aide de son id

        ContentValues values = new ContentValues();

        values.put(COL_SCOREMATH, register.getScoreMath());


        return getDB().update(TABLE_USERS, values, COL_IDU + " = '" + register.getIdu() + "'", null);

    }



    public Register checkUser(String login, String password) {


        Cursor cursor = getDB().rawQuery("SELECT * FROM " + TABLE_USERS + " WHERE " + COL_LOGIN + "=? AND " + COL_PASSWORD + "=?", new String[]{login, password});
        return cursorToFirstRegister(cursor);
    }

    public List<Register> selectAll() {

        Cursor cursor = getDB().rawQuery("SELECT * FROM " + TABLE_USERS, null);

        return cursorToListRegister(cursor);
    }

    private List<Register> cursorToListRegister(Cursor cursor) {

        int indexIdu = cursor.getColumnIndex(COL_IDU);
        int indexLogin = cursor.getColumnIndex(COL_LOGIN);
        int indexPassword = cursor.getColumnIndex(COL_PASSWORD);
        int indexScoreQuest = cursor.getColumnIndex(COL_SCOREQUEST);
        int indexScoreMath = cursor.getColumnIndex(COL_SCOREMATH);

        // Declaration et initialisation d'une liste de question
        ArrayList<Register> liste = new ArrayList<>();

        while (cursor.moveToNext()) {

            // Création d'une question
            Register register = new Register();
            register.setIdu(cursor.getInt(indexIdu));
            register.setLogin(cursor.getString(indexLogin));
            register.setPassword(cursor.getString(indexPassword));
            register.setScoreQuest(cursor.getInt(indexScoreQuest));
            register.setScoreMath(cursor.getInt(indexScoreMath));

            // Ajout dans la liste
            liste.add(register);
        }

        // Fermeture du cursor
        cursor.close();

        //
        return liste;
    }


    private Register cursorToFirstRegister(Cursor cursor) {

        // Récupére l'index des champs
        int indexIdu = cursor.getColumnIndex(COL_IDU);
        int indexLogin = cursor.getColumnIndex(COL_LOGIN);
        int indexPassword = cursor.getColumnIndex(COL_PASSWORD);
        int indexScoreQuest = cursor.getColumnIndex(COL_SCOREQUEST);
        int indexScoreMath = cursor.getColumnIndex(COL_SCOREMATH);


        // Declaration d'une question
        Register register = null;

        if (cursor.getCount() > 0) {

            cursor.moveToFirst();

            // Création d'une question
            register = new Register();
            register.setIdu(cursor.getInt(indexIdu));
            register.setLogin(cursor.getString(indexLogin));
            register.setPassword(cursor.getString(indexPassword));
            register.setScoreQuest(cursor.getInt(indexScoreQuest));
            register.setScoreMath(cursor.getInt(indexScoreMath));

        }

        // Fermeture du cursor
        cursor.close();
        return register;
    }
}