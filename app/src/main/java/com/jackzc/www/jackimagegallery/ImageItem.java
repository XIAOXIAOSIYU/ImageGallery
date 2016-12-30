package com.jackzc.www.jackimagegallery;

import android.graphics.Bitmap;

public class ImageItem {

    private Bitmap image;
    private String title;

    public ImageItem(Bitmap _image, String _title) {
        super();
        this.image = _image;
        this.title = _title;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
