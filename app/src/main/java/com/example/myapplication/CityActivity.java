package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;
import java.util.Locale;

public class CityActivity extends AppCompatActivity {

    private ArrayList<SiteCategory> categories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        loadLocale();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city);

        DataManager.getInstance().initializeData(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        // Set city name and description
        TextView cityNameTextView = findViewById(R.id.city_name);
        TextView cityDescriptionTextView = findViewById(R.id.city_description);

        cityNameTextView.setText(R.string.city_name);
        cityDescriptionTextView.setText(R.string.city_description);

        // Get categories from DataManager
        DataManager dataManager = DataManager.getInstance();
        categories = dataManager.getCategories();

        // Set up the ListView with categories
        ListView categoriesListView = findViewById(R.id.categories_list_view);
        CategoryAdapter adapter = new CategoryAdapter(this, categories);
        categoriesListView.setAdapter(adapter);

        // Set click listener for categories
        categoriesListView.setOnItemClickListener((parent, view, position, id) -> {
            SiteCategory category = categories.get(position);
            Intent intent = new Intent(CityActivity.this, CategoryDetailActivity.class);
            intent.putExtra("CATEGORY_NAME", category.getName());
            intent.putExtra("CATEGORY_POSITION", position);
            startActivity(intent);
        });
    }

    // Méthode pour appliquer la langue sauvegardée
    private void loadLocale() {
        SharedPreferences prefs = getSharedPreferences("Settings", MODE_PRIVATE);
        String language = prefs.getString("App_Lang", "en");
        Locale locale = new Locale(language);
        Locale.setDefault(locale);

        Configuration config = new Configuration();
        config.locale = locale;
        getResources().updateConfiguration(config, getResources().getDisplayMetrics());
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
