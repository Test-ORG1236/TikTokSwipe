package com.personal.medios.VideoUtils;

import android.net.Uri;

import java.util.List;

public class ImageSequence extends ObjetoMedia{
    private List<Uri> ImagesUris;
    private String author, title;
    private int currentIndex = 0;
    public ImageSequence(List<Uri> ImagesUris, String author, String title, int likes){
        super(ImagesUris.get(0), likes, author, title);
        this.ImagesUris = ImagesUris;

    }


}
