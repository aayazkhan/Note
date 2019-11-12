package com.transo.note;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.transo.note.model.Note;
import com.transo.note.viewModels.EditNoteViewModel;
import com.transo.note.viewModels.NewNoteViewModel;

import java.util.Date;

public class EditNoteActivity extends AppCompatActivity {

    private EditText editText_note;

    private EditNoteViewModel mViewModel;
    private FloatingActionButton floatingActionButtonDelete;

    private Note note;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        savedInstanceState = getIntent().getExtras();

        note = (Note) savedInstanceState.getSerializable("note");

        initViewModel();

        editText_note = findViewById(R.id.EditText_Note_title);

        editText_note.setText(note.getTitle());
        editText_note.setSelection(note.getTitle().length());

        floatingActionButtonDelete = findViewById(R.id.fab_delete_note);
        floatingActionButtonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewModel.deleteNote(note);
                finish();
            }
        });


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void initViewModel() {
        mViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(EditNoteViewModel.class);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        String textValue = editText_note.getText().toString();
        note.setTitle(textValue);
        note.setDate(new Date());
        mViewModel.updateNote(note);
    }


}
