package com.personal.medios;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.VideoView;

import androidx.annotation.NonNull;

import com.personal.medios.VideoUtils.ObjetoVideo;

public class VideoViewHolder extends PostHolder<ObjetoVideo> {

    VideoView vv;
    @SuppressLint("ClickableViewAccessibility")
    public VideoViewHolder(@NonNull View view){
        super(view);
        vv = new VideoView(view.getContext());
        vv.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT));
        this.contentContainer.addView(vv);
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
    public void setInfo(ObjetoVideo post){
        Log.i("HolderDebug", "post: "+post);
        super.setInfo(post);
        vv.setVideoURI(post.getUri());
        Log.i("Swipe", "Creating video: " + post.getUri().toString());

    }
}
