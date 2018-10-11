package com.sporksoft.affirm.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Photos {
    public static final String PAGE = "page";
    public static final String TOTAL_PAGES = "pages";
    public static final String TOTAL_RESULTS = "total";
    public static final String COUNT = "perpage";
    public static final String PHOTO = "photo";

    @SerializedName(PHOTO)
    List<Photo> mPhoto;

    @SerializedName(PAGE)
    int mPage;

    @SerializedName(TOTAL_PAGES)
    String mTotalPages;

    @SerializedName(TOTAL_RESULTS)
    String mTotalResults;

    @SerializedName(COUNT)
    int mCount;

    public Photos() {
    }

    public List<Photo> getPhoto() {
        return mPhoto;
    }

    public void setPhoto(List<Photo> photo) {
        mPhoto = photo;
    }
}
