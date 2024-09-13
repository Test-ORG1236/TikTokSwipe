package com.personal.medios.post;

import android.view.View;
import androidx.annotation.NonNull;

import com.personal.medios.api.RetrofitController;

public interface PostHolderFactory {
    public PostHolder createPostHolder(@NonNull View view, PostHolderObservable observer, RetrofitController rtc);
}
