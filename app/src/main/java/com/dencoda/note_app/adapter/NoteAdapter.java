package com.dencoda.note_app.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dencoda.note_app.R;
import com.dencoda.note_app.note.Note;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {

    public interface OnItemClick {
        void onClick(Note note);
    }

    private List<Note> list;
    private OnItemClick listener;

    public NoteAdapter(List<Note> list, OnItemClick listener) {
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_note, parent, false);
        return new NoteViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder h, int position) {
        Note n = list.get(position);

        h.txtTitle.setText(n.title);
        h.txtContent.setText(n.content);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        h.txtTime.setText(sdf.format(new Date(n.time)));

        h.itemView.setOnClickListener(v -> listener.onClick(n));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class NoteViewHolder extends RecyclerView.ViewHolder {
        TextView txtTitle, txtContent, txtTime;

        public NoteViewHolder(@NonNull View v) {
            super(v);
            txtTitle = v.findViewById(R.id.txtTitle);
            txtContent = v.findViewById(R.id.txtContent);
            txtTime = v.findViewById(R.id.txtTime);
        }
    }
}

