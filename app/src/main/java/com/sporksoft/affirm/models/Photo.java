package com.sporksoft.affirm.models;

import com.google.gson.annotations.SerializedName;

public class Photo {
    public static final String ID = "id";
    public static final String OWNER = "owner";
    public static final String SECRET = "secret";
    public static final String SERVER = "server";
    public static final String FARM = "farm";
    public static final String TITLE = "title";
    public static final String PUBLIC = "ispublic";
    public static final String FRIEND = "isfriend";
    public static final String FAMILY = "isfamily";
    public static final String THUMB_URL = "url_s";

    @SerializedName(ID)
    private String mId;

    @SerializedName(OWNER)
    private String mOwner;

    @SerializedName(SECRET)
    private String mSecret;

    @SerializedName(SERVER)
    private String mServer;

    @SerializedName(FARM)
    private String mFarm;

    @SerializedName(TITLE)
    private String mTitle;

    @SerializedName(PUBLIC)
    private int mPublic;

    @SerializedName(FRIEND)
    private int mFriend;

    @SerializedName(FAMILY)
    private int mFamily;

    @SerializedName(THUMB_URL)
    private String mThumbUrl;


    public Photo() {
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getOwner() {
        return mOwner;
    }

    public void setOwner(String owner) {
        mOwner = owner;
    }

    public String getSecret() {
        return mSecret;
    }

    public void setSecret(String secret) {
        mSecret = secret;
    }

    public String getServer() {
        return mServer;
    }

    public void setServer(String server) {
        mServer = server;
    }

    public String getFarm() {
        return mFarm;
    }

    public void setFarm(String farm) {
        mFarm = farm;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public int getPublic() {
        return mPublic;
    }

    public void setPublic(int aPublic) {
        mPublic = aPublic;
    }

    public int getFriend() {
        return mFriend;
    }

    public void setFriend(int friend) {
        mFriend = friend;
    }

    public int getFamily() {
        return mFamily;
    }

    public void setFamily(int family) {
        mFamily = family;
    }

    public String getThumbUrl() {
        return mThumbUrl;
    }

    public void setThumbUrl(String thumbUrl) {
        mThumbUrl = thumbUrl;
    }
}
