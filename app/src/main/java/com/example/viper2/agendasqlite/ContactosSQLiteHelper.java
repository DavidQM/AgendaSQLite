package com.example.viper2.agendasqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Viper2 on 24/04/2017.
 */

public class ContactosSQLiteHelper extends SQLiteOpenHelper {

    //string para crear la tabla
    String sqlCreate = "CREATE TABLE Contactos (" +
            "id         INTEGER PRIMARY KEY AUNTOINCREMENT," +
            "nombre     TEXT," +
            "telefono   TEXT," +
            "correo     TEXT)";

    public ContactosSQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    db.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    db.execSQL("DROP TABLE IF EXIST Contantos");//salte si la tabla ya existe
        db.execSQL(sqlCreate);// si no existe creela
    }
}
