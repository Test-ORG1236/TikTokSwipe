package com.personal.medios.post;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.personal.medios.R;
import com.personal.medios.api.models.FeedModel;
import com.squareup.picasso.Picasso;

public class ImageSequenceHolder extends PostHolder{
    ViewPager2 sequencePager;
    @SuppressLint("ClickableViewAccessibility")
    public ImageSequenceHolder(@NonNull View view){
        super(view);
        sequencePager = new ViewPager2(view.getContext());
        setupView(sequencePager);

    }



    @Override
    public void setInfo(FeedModel post){
        super.setInfo(post);
        sequencePager.setAdapter(new ImageSlidePagerAdapter(post));
    }

    private static class ImageSlidePagerAdapter extends RecyclerView.Adapter<ImageSlidePagerAdapter.ImageSlideHolder>{
        private final FeedModel sequence;

        public ImageSlidePagerAdapter(FeedModel sequence) {
            this.sequence = sequence;
        }

        @NonNull
        @Override
        public ImageSlideHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ImageSlideHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.image_slide, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull ImageSlideHolder holder, int position) {
            Uri u = sequence.getUri(position);
            Log.i("SwipeDebug", "Adding uri: "+u.toString());
            Picasso.get()
                    .load(u)

                    .into((ImageView) holder.iv);
        }

        @Override
        public int getItemCount() {
            return sequence.getFiles().size();
        }

        static private class ImageSlideHolder extends RecyclerView.ViewHolder{
            public ImageView iv;
            public ImageSlideHolder(@NonNull View itemView) {
                super(itemView);
                iv = itemView.findViewById(R.id.image_slide);
            }
        }
    }
}
