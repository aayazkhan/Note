package com.transo.note.model;

import android.view.View;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Date;

@Entity
public class Note implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;
    private Date date;

    @Ignore
    public Note() {
    }

    @Ignore
    public Note(String title, Date date) {
        this.title = title;
        this.date = date;
    }

    public Note(int id, String title, Date date) {
        this.id = id;
        this.title = title;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public interface OnEditClickListener {
        void onClick(View view, int position, Note note);
    }
}
