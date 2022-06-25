package com.example.ecampus;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "biodatadiri";
    public static final String TABLE_NAME = "biodata";
    public static final int DATABASE_VERSION = 1;
    public DataHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE_NAME + " (id INTEGER PRIMARY KEY, nim TEXT, nama TEXT, tgl TEXT, jk TEXT, alamat TEXT)";
        db.execSQL(sql);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    // INSERT TO DATABASE
    public boolean insertData(String nim, String nama, String tgl, String jk, String alamat){
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("nim", nim);
            contentValues.put("nama", nama);
            contentValues.put("tgl", tgl);
            contentValues.put("jk", jk);
            contentValues.put("alamat", alamat);
            db.insert(TABLE_NAME, null, contentValues);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public boolean updateData(String nim, String nama, String tgl, String jk, String alamat){
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            db.execSQL("UPDATE " + TABLE_NAME + " SET nama='" + nama + "', tgl='" + tgl + "', jk='" + jk + "', alamat='" + alamat + "' WHERE nim='" + nim + "'");
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public Cursor getData(){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public Cursor getOneData(String nim){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE nim='" + nim + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public void deleteOneData(String nim){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + TABLE_NAME + " WHERE nim='" + nim + "'";
        db.execSQL(query);
    }
}
