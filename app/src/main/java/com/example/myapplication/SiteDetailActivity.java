package com.example.myapplication;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.Locale;

public class SiteDetailActivity extends AppCompatActivity {

    private String siteName;
    private String sitePhone;
    private String siteEmail;
    private String siteDescription;
    private String siteAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        loadLocale();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_site_detail);

        DataManager.getInstance().initializeData(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        // Récupération des données
        Intent intent = getIntent();
        if (intent != null) {
            siteName = intent.getStringExtra("SITE_NAME");
            sitePhone = intent.getStringExtra("SITE_PHONE");
            siteEmail = intent.getStringExtra("SITE_EMAIL");
            int siteImageResId = intent.getIntExtra("SITE_IMAGE", R.mipmap.placeholder_image);
            siteDescription = intent.getStringExtra("SITE_DESCRIPTION");
            siteAddress = intent.getStringExtra("SITE_ADDRESS");

            TextView sitePhoneTextView = findViewById(R.id.site_phone);
            TextView siteEmailTextView = findViewById(R.id.site_email);
            ImageView siteImageView = findViewById(R.id.site_image);

            TextView siteNameTextView = findViewById(R.id.site_name);
            TextView siteDescriptionTextView = findViewById(R.id.site_description);
            TextView siteAddressTextView = findViewById(R.id.site_address);

            siteNameTextView.setText(siteName);
            siteDescriptionTextView.setText(siteDescription);
            siteAddressTextView.setText(siteAddress);

            sitePhoneTextView.setText(sitePhone != null ? sitePhone : "N/A");
            siteEmailTextView.setText(siteEmail != null ? siteEmail : "N/A");
            siteImageView.setImageResource(siteImageResId);
        }

        // Boutons d'action
        Button callButton = findViewById(R.id.call_button);
        Button smsButton = findViewById(R.id.sms_button);
        Button emailButton = findViewById(R.id.email_button);

        callButton.setOnClickListener(v -> makePhoneCall());
        smsButton.setOnClickListener(v -> sendSMS());
        emailButton.setOnClickListener(v -> sendEmail());
    }

    // 🔄 Méthode pour charger la langue sauvegardée
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
        Intent intent = new Intent(this, CategoryDetailActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
        finish();
        return true;
    }

    private void makePhoneCall() {
        if (sitePhone == null || sitePhone.trim().isEmpty()) {
            Toast.makeText(this, "Numéro de téléphone invalide", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + sitePhone));

        try {
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            Log.e("IntentError", "Aucune application pour passer des appels", e);
            Toast.makeText(this, "Aucune application d'appel disponible", Toast.LENGTH_SHORT).show();
        }
    }

    private void sendSMS() {
        if (sitePhone == null || sitePhone.trim().isEmpty()) {
            Toast.makeText(this, "Numéro de téléphone invalide", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("smsto:" + sitePhone));

        try {
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            Log.e("IntentError", "Aucune application pour envoyer un SMS", e);
            Toast.makeText(this, "Aucune application SMS disponible", Toast.LENGTH_SHORT).show();
        }
    }

    private void sendEmail() {
        if (siteEmail == null || siteEmail.trim().isEmpty()) {
            Toast.makeText(this, "Adresse e-mail invalide", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:" + siteEmail));

        try {
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            Log.e("IntentError", "Aucune application de messagerie", e);
            Toast.makeText(this, "Aucune application e-mail disponible", Toast.LENGTH_SHORT).show();
        }
    }
}
