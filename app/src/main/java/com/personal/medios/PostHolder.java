package com.personal.medios;

import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.personal.medios.VideoUtils.ObjetoMedia;

public abstract class PostHolder<TipoPost extends ObjetoMedia> extends RecyclerView.ViewHolder{
    TipoPost post;
    TextView tvTituloVideo, tvAutorVideo, tvLikes;
    FrameLayout contentContainer;

    public PostHolder(@NonNull View view){
        super(view);
        tvTituloVideo = view.findViewById(R.id.titulo_video);
        tvAutorVideo = view.findViewById(R.id.autor_video);
        tvLikes = view.findViewById(R.id.tvlikes);
        contentContainer = view.findViewById(R.id.content_container);
    }
    public void setInfo(TipoPost post){
        this.post = post;
        Log.i("HolderDebug", "post: " + post);
        tvTituloVideo.setText(post.getTitle());
        tvAutorVideo.setText(post.getAuthor());
        tvLikes.setText(String.valueOf(post.getLikes()));
    }
}
