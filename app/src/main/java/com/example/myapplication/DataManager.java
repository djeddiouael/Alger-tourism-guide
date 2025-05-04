package com.example.myapplication;

import android.content.Context;
import java.util.ArrayList;

// Singleton class to manage data across activities
public class DataManager {
    private static DataManager instance;
    private ArrayList<SiteCategory> categories;

    private DataManager() {
        categories = new ArrayList<>();
    }

    public static synchronized DataManager getInstance() {
        if (instance == null) {
            instance = new DataManager();
        }
        return instance;
    }

    public void initializeData(Context context) {
        if (categories.size() > 0) {
            return; // Data already initialized
        }

        // Tourist Attractions Category
        SiteCategory touristAttractions = new SiteCategory(context.getString(R.string.category_tourist_attractions));

        TouristSite site1 = new TouristSite(
                context.getString(R.string.site_casbah_name),
                context.getString(R.string.site_casbah_description),
                "+213 21 XX XX XX",
                "casbah@tourism.dz",
                context.getString(R.string.site_casbah_address)
        );

        site1.addImageResourceId(R.drawable.casbah);
        touristAttractions.addSite(site1);

        TouristSite site2 = new TouristSite(
                context.getString(R.string.notredame_name),
                context.getString(R.string.notredame_description),
                "+213 21 XX XX XX",
                "notredame@tourism.dz",
                context.getString(R.string.notredame_address)
        );

        site2.addImageResourceId(R.drawable.notre_dame);
        touristAttractions.addSite(site2);

        categories.add(touristAttractions);

        // Hotels & Restaurants Category
        SiteCategory hotelsRestaurants = new SiteCategory(context.getString(R.string.category_hotels_restaurants));

        TouristSite hotel1 = new TouristSite(
                context.getString(R.string.site_el_aurassi_name),
                context.getString(R.string.site_el_aurassi_description),
                "+213 21 74 82 52",
                "reservation@elaurassi.com",
                context.getString(R.string.site_el_aurassi_address)
        );

        hotel1.addImageResourceId(R.drawable.elaurassi);
        hotelsRestaurants.addSite(hotel1);

        TouristSite restaurant1 = new TouristSite(
                context.getString(R.string.site_le_lyre_name),
                context.getString(R.string.site_le_lyre_description),
                "+213 21 XX XX XX",
                "lelyre@restaurant.dz",
                context.getString(R.string.site_le_lyre_address)
        );

        restaurant1.addImageResourceId(R.drawable.restaurant);
        hotelsRestaurants.addSite(restaurant1);

        categories.add(hotelsRestaurants);

        // Public Gardens Category
        SiteCategory publicGardens = new SiteCategory(context.getString(R.string.category_public_gardens));

        TouristSite garden1 = new TouristSite(
                context.getString(R.string.site_hamma_name),
                context.getString(R.string.site_hamma_description),
                "+213 21 XX XX XX",
                "hamma@garden.dz",
                context.getString(R.string.site_hamma_address)
        );

        garden1.addImageResourceId(R.drawable.jardinessai);
        publicGardens.addSite(garden1);

        categories.add(publicGardens);
    }

    public ArrayList<SiteCategory> getCategories() {
        return categories;
    }

    public SiteCategory getCategoryByPosition(int position) {
        if (position >= 0 && position < categories.size()) {
            return categories.get(position);
        }
        return null;
    }


    public TouristSite getSite(int categoryPosition, int sitePosition) {
        SiteCategory category = getCategoryByPosition(categoryPosition);
        if (category != null && sitePosition >= 0 && sitePosition < category.getSites().size()) {
            return category.getSites().get(sitePosition);
        }
        return null;
    }
}

