package com.example.myapplication;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Button;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.SharedPreferences;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Charger la langue sauvegardée avant d'afficher l'interface
        loadLocale();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Barre d'outils (toolbar)
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Affichage de la description de l'application et des noms des étudiants
        TextView appDescriptionTextView = findViewById(R.id.app_description);
        TextView studentNamesTextView = findViewById(R.id.student_names);

        appDescriptionTextView.setText(R.string.app_description);
        studentNamesTextView.setText(R.string.student_names);

        // Bouton pour accéder à CityActivity
        Button exploreButton = findViewById(R.id.explore_button);
        exploreButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, CityActivity.class);
            startActivity(intent);
        });
    }

    // Chargement du menu (langue)
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    // Gestion des clics sur les éléments du menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_language_en) {
            changeLanguage("en");
            restartActivity();
            return true;
        } else if (id == R.id.action_language_ar) {
            changeLanguage("ar");
            restartActivity();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // Changer la langue et sauvegarder dans les préférences
    private void changeLanguage(String languageCode) {
        Locale locale = new Locale(languageCode);
        Locale.setDefault(locale);

        Configuration config = new Configuration();
        config.locale = locale;

        getResources().updateConfiguration(config, getResources().getDisplayMetrics());

        // Sauvegarder le choix de langue
        SharedPreferences.Editor editor = getSharedPreferences("Settings", MODE_PRIVATE).edit();
        editor.putString("App_Lang", languageCode);
        editor.apply();
    }

    // Charger la langue depuis les préférences
    private void loadLocale() {
        SharedPreferences prefs = getSharedPreferences("Settings", MODE_PRIVATE);
        String language = prefs.getString("App_Lang", "en"); // Valeur par défaut : anglais
        Locale locale = new Locale(language);
        Locale.setDefault(locale);

        Configuration config = new Configuration();
        config.locale = locale;
        getResources().updateConfiguration(config, getResources().getDisplayMetrics());
    }

    // Relancer proprement l'activité avec animation
    private void restartActivity() {
        Intent intent = getIntent();
        finish();
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }
}
