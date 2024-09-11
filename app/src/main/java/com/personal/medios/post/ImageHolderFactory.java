package com.personal.medios.post;

import android.view.View;

import androidx.annotation.NonNull;

public class ImageHolderFactory implements PostHolderFactory{
    @Override
    public PostHolder createPostHolder(@NonNull View view) {
        return new ImageHolder(view);
    }
}
