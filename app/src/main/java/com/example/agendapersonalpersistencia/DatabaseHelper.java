package com.example.agendapersonalpersistencia;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "BBDDContactos.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_CLIENTES = "contactos";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NOMBRE = "nombre";
    public static final String COLUMN_MOVIL = "movil";
    public static final String COLUMN_EMAIL = "email";


    private static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_CLIENTES + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_NOMBRE + " TEXT NOT NULL, " +
                    COLUMN_MOVIL + " TEXT NOT NULL, " +
                    COLUMN_EMAIL + " TEXT);" ;


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CLIENTES);
        onCreate(db);
    }
}
