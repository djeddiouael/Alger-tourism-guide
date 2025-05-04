package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;

public class SiteAdapter extends ArrayAdapter<TouristSite> {
    
    public SiteAdapter(Context context, ArrayList<TouristSite> sites) {
        super(context, 0, sites);
    }
    
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        TouristSite site = getItem(position);
        
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_site, parent, false);
        }
        
        // Lookup view for data population
        TextView siteNameTextView = convertView.findViewById(R.id.site_name);
        ImageView siteImageView = convertView.findViewById(R.id.site_image);
        
        // Populate the data into the template view using the data object
        siteNameTextView.setText(site.getName());
        siteImageView.setImageResource(site.getFirstImageResourceId());
        
        // Return the completed view to render on screen
        return convertView;
    }
}

