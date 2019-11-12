package com.transo.note.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.transo.note.R;

import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.MyViewHolder> {

    private Context context;
    private List<Note> notes;
    private Note.OnEditClickListener onEditClickListener;

    public NoteAdapter(Context context, List<Note> notes, Note.OnEditClickListener onEditClickListener) {
        this.context = context;
        this.notes = notes;
        this.onEditClickListener = onEditClickListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.note_item_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        final Note note = notes.get(position);

        holder.textViewTitle.setText(note.getTitle());

        holder.floatingActionButtonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onEditClickListener.onClick(view, position, note);
            }
        });
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewTitle;
        public FloatingActionButton floatingActionButtonEdit;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewTitle = itemView.findViewById(R.id.textView_Note_title);
            floatingActionButtonEdit = itemView.findViewById(R.id.floatingActionButton_Note_Edit);

        }
    }
}
