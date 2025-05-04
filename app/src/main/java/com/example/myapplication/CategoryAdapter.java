package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;

public class CategoryAdapter extends ArrayAdapter<SiteCategory> {
    
    public CategoryAdapter(Context context, ArrayList<SiteCategory> categories) {
        super(context, 0, categories);
    }
    
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        SiteCategory category = getItem(position);
        
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_category, parent, false);
        }
        
        // Lookup view for data population
        TextView categoryNameTextView = convertView.findViewById(R.id.category_name);
        TextView siteCountTextView = convertView.findViewById(R.id.site_count);
        
        // Populate the data into the template view using the data object
        categoryNameTextView.setText(category.getName());
        siteCountTextView.setText(getContext().getString(R.string.site_count, category.getSites().size()));
        
        // Return the completed view to render on screen
        return convertView;
    }
}

