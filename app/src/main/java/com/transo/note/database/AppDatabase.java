package com.transo.note.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.transo.note.model.Note;

@Database(entities = {Note.class}, version = 1, exportSchema = false)
@TypeConverters(DateConverter.class)
public abstract class AppDatabase extends RoomDatabase {

    public static final String DATABASE_NAME = "notes.db";

    public static volatile AppDatabase instance = null;
    public static final Object LOCK = new Object();

    public abstract NoteDao noteDao();

    public static AppDatabase getInstance(Context context) {

        if (instance == null) {
            synchronized (LOCK) {
                if (instance == null) {
                    instance = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, DATABASE_NAME).build();
                }
            }
        }

        return instance;
    }
}
