package com.transo.note.viewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.transo.note.database.AppRepository;
import com.transo.note.model.Note;

public class NewNoteViewModel extends AndroidViewModel {

    private AppRepository mRepository;

    public NewNoteViewModel(@NonNull Application application) {
        super(application);
        mRepository = AppRepository.getInstance(application.getApplicationContext());
    }

    public void saveNote(Note note) {
        mRepository.addNote(note);
    }
}
