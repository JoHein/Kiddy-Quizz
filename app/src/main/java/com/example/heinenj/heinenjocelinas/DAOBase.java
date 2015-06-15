package com.example.heinenj.heinenjocelinas;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by fbm on 26/03/14.
 */
public abstract class DAOBase {

    // TAG pour le log
    private static final String TAG = "DBAdapter";

    //
    private SQLiteDatabase db;
    private DBHelper databaseHelper;
   // private Register userReg;

    public DAOBase(){};

    // refaire avec getWritableDatabase(); et getReadableDatabase();
    public DAOBase(Context context) {
        databaseHelper = new DBHelper(context, null);
    }

    // On ouvre la base de données en écriture
    public void open() throws SQLException {
        db = databaseHelper.getWritableDatabase();
    }

    //on ferme l'accès à la base de données
    public void close() {
        db.close();
    }

    //
    public SQLiteDatabase getDB(){
        return db;
    }



}

