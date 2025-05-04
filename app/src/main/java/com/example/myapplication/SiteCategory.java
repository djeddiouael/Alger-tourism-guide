package com.example.myapplication;

import java.util.ArrayList;

public class SiteCategory {
    private String name;
    private ArrayList<TouristSite> sites;
    
    public SiteCategory(String name) {
        this.name = name;
        this.sites = new ArrayList<>();
    }
    
    public void addSite(TouristSite site) {
        sites.add(site);
    }
    
    public String getName() {
        return name;
    }
    
    public ArrayList<TouristSite> getSites() {
        return sites;
    }
}

