package com.personal.medios.post;

import android.view.View;

import androidx.annotation.NonNull;

public class ImageSequenceHolderFactory implements PostHolderFactory{
    @Override
    public PostHolder createPostHolder(@NonNull View view) {
        return new ImageSequenceHolder(view);
    }
}
