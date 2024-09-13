package com.personal.medios.post;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.personal.medios.R;
import com.personal.medios.api.RetrofitController;
import com.personal.medios.api.models.FeedModel;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class PostHolder extends RecyclerView.ViewHolder implements PostHolderObserver {
    FeedModel post;
    TextView tvTituloVideo, tvAutorVideo, tvLikes;
    ImageView heartImage;
    FrameLayout contentContainer;
    private RetrofitController rtc;
    @SuppressLint("ClickableViewAccessibility")
    public PostHolder(@NonNull View view, PostHolderObservable o, RetrofitController rtc){
        super(view);
        this.rtc = rtc;
        tvTituloVideo = view.findViewById(R.id.titulo_video);
        tvAutorVideo = view.findViewById(R.id.autor_video);
        tvLikes = view.findViewById(R.id.tvlikes);
        contentContainer = view.findViewById(R.id.content_container);
        heartImage = view.findViewById(R.id.heartImg);
        heartImage.setOnTouchListener((v, me) -> {
                try {
                    likePost();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                return false;
            }
        );
        o.attatchPost(this);

    }

    public void likePost() throws IOException {
        if (post!=null){
            Call<Void> likeCall = rtc.getVideoApi().likeVideo(post.getId());
            post.setLikes(post.getLikes()+1);
            tvLikes.setText(String.valueOf(post.getLikes()));
            likeCall.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {

                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {

                }
            });
        }
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

    public void NotifySwipedTo(){
        Log.i("SwipeDebug", "Swiped into: "+tvTituloVideo.getText());
    }
    public void NotifySwipedFrom(){
        Log.i("SwipeDebug", "Swiped from: "+tvTituloVideo.getText());
    }
}
