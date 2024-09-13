package com.personal.medios.post;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.VideoView;

import androidx.annotation.NonNull;

import com.personal.medios.api.RetrofitController;
import com.personal.medios.api.models.FeedModel;

public class VideoHolder extends PostHolder{

    VideoView vv;
    @SuppressLint("ClickableViewAccessibility")
    public VideoHolder(@NonNull View view, PostHolderObservable observer, RetrofitController rtc){
        super(view,observer, rtc);
        vv = new VideoView(view.getContext());
        setupView(vv);
        vv.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.start();
                mp.setLooping(true);
            }
        });

        vv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (vv.isPlaying()) vv.pause();
                else vv.start();
                return false;
            }
        });

    }



    @Override
    public void setInfo(FeedModel post){
        super.setInfo(post);
        vv.setVideoURI(post.getUri());
        Log.i("Swipe", "Creating video: " + post.getUri().toString());

    }
}
