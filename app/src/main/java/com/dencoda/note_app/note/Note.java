package com.dencoda.note_app.note;

import java.io.Serializable;

public class Note {
    public int id;
    public String title;
    public String content;
    public long time;

    public Note(int id, String title, String content, long time) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.time = time;
    }

    public Note(String title, String content) {
        this.title = title;
        this.content = content;
        this.time = System.currentTimeMillis();
    }
}

