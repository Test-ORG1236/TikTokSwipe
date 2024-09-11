package com.personal.medios.post;

import android.view.View;
import androidx.annotation.NonNull;

public interface PostHolderFactory {
    public PostHolder createPostHolder(@NonNull View view);
}
