package com.loveboyuan.smarttrader;

import android.location.Location;
import android.net.Uri;

import java.io.Serializable;

/**
 * Created by boyuangu on 2015-10-16.
 */
public class Item implements Serializable {

    static User usr=LoginActivity.usr;

    //final int id;
    private String name;
    private int ownerID;
    private Location location;
    private String category;
    private int quantity;
    private String quality;
    private boolean isPrivate;
    private String description;
    private Uri photo;





    public Item(String name, String category, int quantity, String quality, boolean isPrivate, String description, Uri photo){
      //  id = name;
        this.name = name;
        this.category = category;
        this.quantity = quantity;
        this.quality = quality;
        this.isPrivate = isPrivate;
        this.description = description;
        this.photo = photo;
        this.location = null;
        this.ownerID = usr.getMy_id();
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public void setIsPrivate(boolean isPrivate) {
        this.isPrivate = isPrivate;
    }

    public void setPhoto(Uri photo) {
        this.photo = photo;
    }

    public String getCategory() {
        return category;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString(){
        return name;
    }


    public String getName() {
        return name;
    }

    public String getQuality() {
        return quality;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getDescription() {
        return description;
    }

    public boolean isPrivate() {
        return isPrivate;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
    public int getOwnerID() {
        return ownerID;
    }
}
