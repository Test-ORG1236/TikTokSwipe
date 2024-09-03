package com.personal.medios.VideoUtils;

import android.net.Uri;

public class ObjetoMedia implements IMedia{
    private Uri uri;
    private String author, title;
    private int likes;
    public ObjetoMedia(Uri uri, int likes, String author, String title) {
        this.uri = uri;
        this.likes = likes;
        this.author = author;
        this.title = title;
    }

    public Uri getUri(){
        return uri;
    }

    public String getAuthor(){
        return author;
    }

    public String getTitle(){
        return title;
    }

    public int getLikes() {
        return likes;
    }
}
