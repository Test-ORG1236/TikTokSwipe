package com.personal.medios.api;

import android.net.Uri;

import com.google.gson.annotations.Expose;

public class FeedModel {
    @Expose(deserialize = false)
    private String userName;
    private String file, title, type;
    private int userId, likes;

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public Uri getVideoUri(String url) {
        return Uri.parse(url+"/"+userName+"/"+file);
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
