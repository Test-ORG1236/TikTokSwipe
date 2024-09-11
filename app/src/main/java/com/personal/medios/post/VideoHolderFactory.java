package com.personal.medios.post;

import android.view.View;

import androidx.annotation.NonNull;

public class VideoHolderFactory implements PostHolderFactory{
    @Override
    public PostHolder createPostHolder(@NonNull View view) {
        return new VideoHolder(view);
    }
}
