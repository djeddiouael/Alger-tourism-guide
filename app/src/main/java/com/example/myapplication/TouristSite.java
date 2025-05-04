package com.example.myapplication;

import java.util.ArrayList;

public class TouristSite {
    private String name;
    private String description;
    private String phoneNumber;
    private String email;
    private String address;
    private ArrayList<Integer> imageResourceIds;

    public TouristSite(String name, String description, String phoneNumber, String email, String address) {
        this.name = name;
        this.description = description;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        this.imageResourceIds = new ArrayList<>();
    }

    // Add an image resource ID to the site
    public void addImageResourceId(int resourceId) {
        imageResourceIds.add(resourceId);
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public ArrayList<Integer> getImageResourceIds() {
        return imageResourceIds;
    }

    public int getFirstImageResourceId() {
        if (imageResourceIds.size() > 0) {
            return imageResourceIds.get(0);
        }
        return R.mipmap.placeholder_image;
    }
}

