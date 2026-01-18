package com.dencoda.note_app.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.dencoda.note_app.note.Note;

import java.util.ArrayList;
import java.util.List;

public class NoteDB extends SQLiteOpenHelper {
    private static final String DB_NAME = "note.db";
    private static final int DB_VERSION = 1;

    public static final String TABLE = "notes";

    public NoteDB(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "title TEXT," +
                "content TEXT," +
                "time INTEGER)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE);
        onCreate(db);
    }

    // INSERT
    public void insert(Note note) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("title", note.title);
        cv.put("content", note.content);
        cv.put("time", note.time);
        db.insert(TABLE, null, cv);
    }

    // GET ALL
    public List<Note> getAll() {
        List<Note> list = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();

        Cursor c = db.rawQuery(
                "SELECT * FROM " + TABLE + " ORDER BY time DESC", null);

        while (c.moveToNext()) {
            list.add(new Note(
                    c.getInt(0),
                    c.getString(1),
                    c.getString(2),
                    c.getLong(3)
            ));
        }
        c.close();
        return list;
    }

    // DELETE
    public void delete(int id) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE, "id=?", new String[]{String.valueOf(id)});
    }
}
