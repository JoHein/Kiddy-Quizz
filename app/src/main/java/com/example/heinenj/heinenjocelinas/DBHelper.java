package com.example.heinenj.heinenjocelinas;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by fbm on 26/03/14.
 */
public class DBHelper extends SQLiteOpenHelper {

    // VERSION de la bdd, permet les mises à jour des tables et champs au lancement de l'application
    private static final int VERSION = 15;

    // NOM de la base
    private static final String DATABASE_NAME = "database_enfer";


    // TAG pour le log
    private static final String TAG = "DBHelper";



    // Constructeur
    public DBHelper(Context context, SQLiteDatabase.CursorFactory factory) {
        super(context, DATABASE_NAME, factory, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // Créer la table question
        db.execSQL(QuestionDAO.CREATE_TABLE);
        db.execSQL(RegisterDAO.CREATE_TABLE);

        // Insérer les données
        for (String insert : QuestionDAO.getInsertSQL()) {
            db.execSQL(insert);
        }


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // Log
        Log.w(TAG, "UPGRADING DATABASE FROM VERSION " + oldVersion
                + " TO "
                + newVersion + ", WHICH WILL DESTROY ALL OLD DATA !");

        // DROP
        db.execSQL(QuestionDAO.DROP_TABLE);
        db.execSQL(RegisterDAO.DROP_TABLE);


        // Relancer la création
        onCreate(db);
    }

}
