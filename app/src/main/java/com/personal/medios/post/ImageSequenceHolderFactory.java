package com.personal.medios.post;

import android.view.View;

import androidx.annotation.NonNull;

import com.personal.medios.api.RetrofitController;

public class ImageSequenceHolderFactory implements PostHolderFactory{
    @Override
    public PostHolder createPostHolder(@NonNull View view, PostHolderObservable observer, RetrofitController rtc) {
        return new ImageSequenceHolder(view, observer,rtc);
    }
}
