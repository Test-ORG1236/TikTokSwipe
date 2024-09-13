package com.personal.medios.post;

import android.media.MediaPlayer;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.personal.medios.api.RetrofitController;
import com.personal.medios.api.models.AudioModel;
import com.personal.medios.api.models.FeedModel;
import com.personal.medios.R;

public class PostWithAudioHolder extends PostHolder{
    private MediaPlayer audioPlayer = null;
    private final TextView tvTituloAudio;
    public PostWithAudioHolder(@NonNull View view, PostHolderObservable o, RetrofitController rtc){
        super(view,o, rtc);
        tvTituloAudio = view.findViewById(R.id.titulo_audio);
    }
    public void setupAudioPlayer(){
        if (audioPlayer==null && post!=null){
            AudioModel audio = post.getAudio();
            if (audio!=null) {
                tvTituloAudio.setText("Audio: " + audio.getTitle());
                audioPlayer = MediaPlayer.create(itemView.getContext(), audio.getUri());
                audioPlayer.setLooping(true);

            }
        }
    }

    @Override
    public void setInfo(FeedModel post) {
        super.setInfo(post);

        setupAudioPlayer();
    }

    @Override
    public void NotifySwipedTo() {
        super.NotifySwipedTo();
        if (audioPlayer !=null) {
            Log.i("SwipeDebug", "play audio, "+audioPlayer);
            audioPlayer.seekTo(0);
            audioPlayer.start();
        }
    }

    @Override
    public void NotifySwipedFrom() {
        super.NotifySwipedFrom();
        if (audioPlayer !=null && audioPlayer.isPlaying()) {
            audioPlayer.pause();
        }
    }

}
