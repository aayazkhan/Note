package com.transo.note.viewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.transo.note.database.AppRepository;
import com.transo.note.model.Note;

public class EditNoteViewModel extends AndroidViewModel {

    private AppRepository mRepository;

    public EditNoteViewModel(@NonNull Application application) {
        super(application);
        mRepository = AppRepository.getInstance(application.getApplicationContext());
    }

    public void updateNote(Note note) {
        mRepository.updateNote(note);
    }

    public void deleteNote(Note note) {
        mRepository.deleteNote(note);
    }
}
