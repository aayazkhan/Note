package com.transo.note;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.transo.note.model.Note;
import com.transo.note.model.NoteAdapter;
import com.transo.note.viewModels.ListActivityViewModel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Note> notes = new ArrayList<>();
    private ListActivityViewModel mViewModel;
    private NoteAdapter mNoteAdapter;

    private RecyclerView mRecyclerView;
    private FloatingActionButton fabAddNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initViewModel();

        mRecyclerView = findViewById(R.id.mRecyclerView);
        initRecyclerView();

        fabAddNote = findViewById(R.id.fabaddnote);
        fabAddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, NewNoteActivity.class));
            }
        });
    }

    private void initViewModel() {

        Observer<List<Note>> noteObserver = new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notes) {
                MainActivity.this.notes.clear();
                MainActivity.this.notes.addAll(notes);
                if (mNoteAdapter == null) {
                    mNoteAdapter = new NoteAdapter(MainActivity.this, MainActivity.this.notes, onEditClickListener);
                    mRecyclerView.setAdapter(mNoteAdapter);
                } else {
                    mNoteAdapter.notifyDataSetChanged();
                }
            }
        };

        mViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(ListActivityViewModel.class);

        mViewModel.getNoteList().observe(MainActivity.this, noteObserver);
    }

    private Note.OnEditClickListener onEditClickListener = new Note.OnEditClickListener() {


        @Override
        public void onClick(View view, int position, Note note) {
            startActivity(
                    new Intent(MainActivity.this, EditNoteActivity.class)
                            .putExtra("note", note)
            );
        }
    };

    private void initRecyclerView() {
        mRecyclerView.hasFixedSize();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.add_sample) {
            addSampleData();
            return true;
        }

        if (id == R.id.delete_all) {
            deleteAllNotes();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void deleteAllNotes() {
        mViewModel.deleteAllNotes();
    }

    private void addSampleData() {
        mViewModel.addSampleData();
    }
}
