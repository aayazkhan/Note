package com.transo.note.database;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.transo.note.model.Note;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class AppRepository {

    private static AppRepository ourInstance;
    private AppDatabase mDatabase;

    private Executor mExecutor = Executors.newSingleThreadExecutor();

    public static AppRepository getInstance(Context context) {
        return ourInstance = new AppRepository(context);
    }

    private AppRepository(Context context) {
        mDatabase = AppDatabase.getInstance(context);
    }

    public LiveData<List<Note>> getNotes() {
        return mDatabase.noteDao().getAll();
    }

    public void addSampleData() {
        mExecutor.execute(new Runnable() {
            @Override
            public void run() {

                List<Note> notes = new ArrayList<Note>();

                notes.add(new Note("title 1", new Date()));
                notes.add(new Note("title 2", new Date()));
                notes.add(new Note("title 3", new Date()));

                mDatabase.noteDao().insert(notes);
            }
        });
    }

    public void deleteAllNotes() {
        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                mDatabase.noteDao().deleteAll();
            }
        });
    }

    public void addNote(final Note note) {
        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                mDatabase.noteDao().insert(note);
            }
        });
    }

    public void updateNote(final Note note) {
        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                mDatabase.noteDao().insert(note);
            }
        });
    }

    public void deleteNote(final Note note) {
        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                mDatabase.noteDao().delete(note);
            }
        });
    }
}
