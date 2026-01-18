package com.dencoda.note_app;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.dencoda.note_app.db.NoteDB;
import com.dencoda.note_app.note.Note;

import java.util.List;

public class DetailActivity extends AppCompatActivity {

    TextView txtTitle, txtContent;
    Button btnDelete;
    NoteDB db;
    int noteId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detail);

        txtTitle = findViewById(R.id.txtTitle);
        txtContent = findViewById(R.id.txtContent);
        btnDelete = findViewById(R.id.btnDelete);

        db = new NoteDB(this);

        noteId = getIntent().getIntExtra("id", -1);

        Note note = null;
        List<Note> notes = db.getAll();
        for (Note n : notes) {
            if (n.id == noteId) {
                note = n;
                break;
            }
        }

        if (note != null) {
            txtTitle.setText(note.title);
            txtContent.setText(note.content);
        }

        btnDelete.setOnClickListener(v -> {
            db.delete(noteId);
            finish();
        });
    }
}