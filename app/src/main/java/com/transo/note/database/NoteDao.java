package com.transo.note.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.transo.note.model.Note;

import java.util.List;

@Dao
public interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Note note);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<Note> notes);

    @Delete
    void delete(Note note);

    @Delete
    void delete(List<Note> notes);

    @Query("SELECT * FROM Note where id=:id")
    Note get(int id);

    @Query("SELECT * FROM Note ORDER BY date DESC ")
    LiveData<List<Note>> getAll();

    @Query("DELETE FROM Note")
    int deleteAll();

    @Query("SELECT COUNT(*) FROM Note ")
    int getCount();

}
