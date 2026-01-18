package com.dencoda.note_app;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Switch;
import com.google.android.material.switchmaterial.SwitchMaterial;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SettingsActivity extends AppCompatActivity {
    private static final String PREFS = "settings";
    private static final String KEY_DARK = "dark_mode";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_settings);

        SwitchMaterial switchDark = findViewById(R.id.switchDark);
        SharedPreferences prefs = getSharedPreferences(PREFS, MODE_PRIVATE);

        boolean isDark = prefs.getBoolean(KEY_DARK, false);

        switchDark.setChecked(isDark);
        AppCompatDelegate.setDefaultNightMode(
                isDark
                        ? AppCompatDelegate.MODE_NIGHT_YES
                        : AppCompatDelegate.MODE_NIGHT_NO
        );

        switchDark.setOnCheckedChangeListener((buttonView, checked) -> {
            prefs.edit().putBoolean(KEY_DARK, checked).apply();

            AppCompatDelegate.setDefaultNightMode(
                    checked
                            ? AppCompatDelegate.MODE_NIGHT_YES
                            : AppCompatDelegate.MODE_NIGHT_NO
            );
        });
    }
}