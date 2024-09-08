package com.personal.medios.VideoUtils;

import android.net.Uri;

public class ObjetoMedia{

    private final String author, title;
    private int likes;
    public ObjetoMedia(int likes, String author, String title) {
        this.likes = likes;
        this.author = author;
        this.title = title;
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
    public void setLikes(int likes) {
        this.likes = likes;
    }
}
