package com.personal.medios.post;

import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.personal.medios.R;
import com.personal.medios.api.models.FeedModel;

public abstract class PostHolder extends RecyclerView.ViewHolder{
    FeedModel post;
    TextView tvTituloVideo, tvAutorVideo, tvLikes;
    FrameLayout contentContainer;

    public PostHolder(@NonNull View view){
        super(view);
        tvTituloVideo = view.findViewById(R.id.titulo_video);
        tvAutorVideo = view.findViewById(R.id.autor_video);
        tvLikes = view.findViewById(R.id.tvlikes);
        contentContainer = view.findViewById(R.id.content_container);
    }
    public final void setupView(View view){
        FrameLayout.LayoutParams p = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
        p.gravity = Gravity.CENTER;
        view.setLayoutParams(p);
        this.contentContainer.addView(view);
    }
    public void setInfo(FeedModel post){
        this.post = post;
        tvTituloVideo.setText(post.getTitle());
        tvAutorVideo.setText(post.getUserName());
        tvLikes.setText(String.valueOf(post.getLikes()));
    }
}
