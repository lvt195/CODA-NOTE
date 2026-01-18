package com.dencoda.note_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dencoda.note_app.adapter.NoteAdapter;
import com.dencoda.note_app.db.NoteDB;
import com.dencoda.note_app.note.Note;
import com.google.android.material.appbar.MaterialToolbar;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    NoteDB db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        db = new NoteDB(this);

        List<Note> notes = db.getAll();

        NoteAdapter adapter = new NoteAdapter(notes, note -> {
            Intent i = new Intent(this, DetailActivity.class);
            i.putExtra("id", note.id);
            startActivity(i);
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        findViewById(R.id.fab).setOnClickListener(v ->
                startActivity(new Intent(this, AddEditActivity.class))
        );

        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    protected void onResume() {
        super.onResume();
        recyclerView.setAdapter(
                new NoteAdapter(db.getAll(), note -> {
                    Intent i = new Intent(this, DetailActivity.class);
                    i.putExtra("id", note.id);
                    startActivity(i);
                })
        );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.xml.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_settings) {
            startActivity(new Intent(this, SettingsActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}