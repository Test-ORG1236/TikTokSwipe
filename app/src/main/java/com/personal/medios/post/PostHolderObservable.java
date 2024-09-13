package com.personal.medios.post;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class PostHolderObservable {
    List<PostHolder> posts;
    public PostHolderObservable(){
        posts = new ArrayList<>();

    }
    public void attatchPost(PostHolder ph){
        posts.add(ph);
    }
    private PostHolder getObserverByPosition(int position){
        for (PostHolder ph : posts){
            if (ph.getAdapterPosition()==position){
                return ph;
            }
        }
        return null;
    }
    public void notifyVisible(int position){
        PostHolder ph = getObserverByPosition(position);
        Log.i("SwipeDebug", "Notifying: " + position + ", "+ph);
        if (ph!=null) ph.NotifySwipedTo();
    }

    public void notifySwipedFrom(int position ){
        PostHolder ph = getObserverByPosition(position);
        if (ph!=null) ph.NotifySwipedFrom();
    }
}
