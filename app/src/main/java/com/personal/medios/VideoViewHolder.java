package com.personal.medios;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.personal.medios.VideoUtils.ObjetoMedia;
import com.personal.medios.api.RetrofitController;
public class VideoViewHolder extends RecyclerView.ViewHolder {
    ObjetoMedia video;

    VideoView vv;
    TextView tvTituloVideo, tvAutorVideo, tvLikes;
    @SuppressLint("ClickableViewAccessibility")
    public VideoViewHolder(@NonNull View view){
        super(view);
        vv = view.findViewById(R.id.videoView);
        tvTituloVideo = view.findViewById(R.id.titulo_video);
        tvAutorVideo = view.findViewById(R.id.autor_video);
        tvLikes = view.findViewById(R.id.tvlikes);
//        ImageView iv = view.findViewById(R.id.heartImg);
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




    public void setVideo(ObjetoMedia video){
        Log.i("Swipe", "Creating video: " + video.getUri().toString());
        this.video = video;
        vv.setVideoURI(video.getUri());
        tvTituloVideo.setText(video.getTitle());
        tvAutorVideo.setText(video.getAuthor());
        tvLikes.setText(String.valueOf(video.getLikes()));




    }
}
