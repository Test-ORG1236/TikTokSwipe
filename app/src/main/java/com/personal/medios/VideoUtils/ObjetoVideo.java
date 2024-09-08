package com.personal.medios.VideoUtils;

import android.net.Uri;

public class ObjetoVideo extends ObjetoMedia {
    private final Uri uri;
    public ObjetoVideo(int likes, String author, String title, Uri uri) {
        super(likes, author, title);
        this.uri = uri;
    }

    public Uri getUri(){
        return uri;
    }
}
