package com.transo.note.viewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.transo.note.database.AppRepository;
import com.transo.note.model.Note;

import java.util.List;

public class ListActivityViewModel extends AndroidViewModel {

    private AppRepository mRepository;
    private LiveData<List<Note>> notes;

    public ListActivityViewModel(@NonNull Application application) {
        super(application);
        mRepository = AppRepository.getInstance(application.getApplicationContext());
        notes = mRepository.getNotes();
    }

    public LiveData<List<Note>> getNoteList() {
        return notes;
    }

    public void addSampleData() {
        mRepository.addSampleData();
    }

    public void deleteAllNotes() {
        mRepository.deleteAllNotes();
    }
}
