package com.transo.note;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import com.transo.note.model.Note;
import com.transo.note.viewModels.NewNoteViewModel;

import java.util.Date;

public class NewNoteActivity extends AppCompatActivity {

    private EditText editText_note;

    private NewNoteViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_note);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initViewModel();

        editText_note = findViewById(R.id.EditText_Note_title);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void initViewModel() {
        mViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(NewNoteViewModel.class);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        String textValue = editText_note.getText().toString();
        if (!TextUtils.isEmpty(textValue)) {
            Note note = new Note(textValue, new Date());
            mViewModel.saveNote(note);
        }
    }
}
