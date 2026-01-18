package com.dencoda.note_app;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.dencoda.note_app.db.NoteDB;
import com.dencoda.note_app.note.Note;

public class AddEditActivity extends AppCompatActivity {
    EditText edtTitle, edtContent;
    Button btnSave;
    NoteDB db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_edit);

        edtTitle = findViewById(R.id.edtTitle);
        edtContent = findViewById(R.id.edtContent);
        btnSave = findViewById(R.id.btnSave);

        db = new NoteDB(this);

        btnSave.setOnClickListener(v -> {
            String title = edtTitle.getText().toString().trim();
            String content = edtContent.getText().toString().trim();

            if (title.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập tiêu đề", Toast.LENGTH_SHORT).show();
                return;
            }

            db.insert(new Note(title, content));
            finish();
        });
    }
}