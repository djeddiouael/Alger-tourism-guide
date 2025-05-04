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

public class CategoryDetailActivity extends AppCompatActivity {

    private ArrayList<TouristSite> sites;
    private int categoryPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        loadLocale();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_detail);

        DataManager.getInstance().initializeData(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        // Get category name and position from intent
        String categoryName = getIntent().getStringExtra("CATEGORY_NAME");
        categoryPosition = getIntent().getIntExtra("CATEGORY_POSITION", 0);

        // Set category name
        TextView categoryNameTextView = findViewById(R.id.category_name);
        categoryNameTextView.setText(categoryName);

        // Get sites from DataManager
        DataManager dataManager = DataManager.getInstance();
        SiteCategory category = dataManager.getCategoryByPosition(categoryPosition);

        if (category != null) {
            sites = category.getSites();

            // Set up the ListView with sites
            ListView sitesListView = findViewById(R.id.sites_list_view);
            SiteAdapter adapter = new SiteAdapter(this, sites);
            sitesListView.setAdapter(adapter);

            // Set click listener for sites
            sitesListView.setOnItemClickListener((parent, view, position, id) -> {
                TouristSite site = sites.get(position);
                // Open SiteDetailActivity and pass the site details
                Intent intent = new Intent(CategoryDetailActivity.this, SiteDetailActivity.class);
                intent.putExtra("SITE_NAME", site.getName());
                intent.putExtra("SITE_DESCRIPTION", site.getDescription());
                intent.putExtra("SITE_PHONE", site.getPhoneNumber());
                intent.putExtra("SITE_EMAIL", site.getEmail());
                intent.putExtra("SITE_ADDRESS", site.getAddress());
                intent.putExtra("SITE_IMAGE", site.getFirstImageResourceId());
                intent.putExtra("CATEGORY_POSITION", categoryPosition);
                intent.putExtra("SITE_POSITION", position);
                startActivity(intent);
            });
        }
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
