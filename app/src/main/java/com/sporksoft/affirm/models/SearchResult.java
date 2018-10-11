package com.sporksoft.affirm.models;


import com.google.gson.annotations.SerializedName;

public class SearchResult {
    public static final String PHOTOS = "photos";
    public static final String STAT = "stat";

    @SerializedName(PHOTOS)
    private Photos mPhotos;

    @SerializedName(STAT)
    private String mStat;

    public Photos getPhotos() {
        return mPhotos;
    }

    public void setPhotos(Photos photos) {
        mPhotos = photos;
    }

    public String getStat() {
        return mStat;
    }

    public void setStat(String stat) {
        mStat = stat;
    }
}
