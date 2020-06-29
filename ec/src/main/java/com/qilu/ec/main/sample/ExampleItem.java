package com.qilu.ec.main.sample;

import android.graphics.drawable.Drawable;

/**
 * A dummy item representing a piece of content.
 */
public class ExampleItem {
    private String id;
    private String itemNumber;
    private Drawable image;
    private Boolean isSaved;

    public Boolean getSaved() {
        return isSaved;
    }

    public void setSaved(Boolean isSaved) {
        this.isSaved = isSaved;
    }

    public ExampleItem(String id, String itemNumber, Drawable image, Boolean isSaved) {
        this.id = id;
        this.itemNumber = itemNumber;
        this.image = image;
        this.isSaved = isSaved;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getItemNumber() {
        return itemNumber;
    }

    public void setItemNumber(String itemNumber) {
        this.itemNumber = itemNumber;
    }

    public Drawable getImage() {
        return image;
    }

    public void setImage(Drawable image) {
        this.image = image;
    }
}