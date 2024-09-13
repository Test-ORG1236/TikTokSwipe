package com.personal.medios.post;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.personal.medios.api.RetrofitController;
import com.personal.medios.api.models.FeedModel;
import com.squareup.picasso.Picasso;

public class ImageHolder extends PostWithAudioHolder{
    ImageView iv;
    @SuppressLint("ClickableViewAccessibility")
    public ImageHolder(@NonNull View view, PostHolderObservable observer, RetrofitController rtc){
        super(view,observer, rtc);
        iv = new ImageView(view.getContext());
        setupView(iv);

    }



    @Override
    public void setInfo(FeedModel post){
        super.setInfo(post);
        Picasso.get()
                .load(post.getUri())

                .into(iv);

        Log.i("Swipe", "Creating image: " + post.getUserName()+", "+post.getFile());

    }
}
