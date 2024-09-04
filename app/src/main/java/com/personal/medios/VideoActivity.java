 package com.personal.medios;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.personal.medios.VideoUtils.ObjetoMedia;
import com.personal.medios.api.FeedModel;
import com.personal.medios.api.FeedPageModel;
import com.personal.medios.api.RetrofitController;
import com.personal.medios.api.UserModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

 public class VideoActivity extends FragmentActivity {

     private String nextCursor = null;
     private ScreenSlidePagerAdapter pagerAdapter;
     static private RetrofitController apiController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_videoswipe);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent i = getIntent();

        String ApiUrl = (String) Objects.requireNonNull(i.getExtras()).get("API_URL");
        apiController = new RetrofitController(ApiUrl);
        ViewPager2 viewPager = findViewById(R.id.swipe_container);
        pagerAdapter = new ScreenSlidePagerAdapter(this);
        viewPager.setAdapter(pagerAdapter);
        loadFeed();

    }

    public void loadFeed(){
        Call<FeedPageModel> callFeedPage;
        if (nextCursor==null) callFeedPage = apiController.getVideoApi().getFeed();
        else callFeedPage = apiController.getVideoApi().getFeed(nextCursor);

        callFeedPage.enqueue(new Callback<FeedPageModel>() {
            @Override
            public void onResponse(Call<FeedPageModel> call, Response<FeedPageModel> response) {
                FeedPageModel b = response.body();
                nextCursor = b.getCursor_token();
                for (FeedModel fm : b.getFeed())
                    pagerAdapter.addFeed(fm);
            }

            @Override
            public void onFailure(Call<FeedPageModel> call, Throwable t) {

            }
        });
    }
    static private class ScreenSlidePagerAdapter extends RecyclerView.Adapter<VideoViewHolder> {

        List<FeedModel> feed = new ArrayList<>();
        VideoActivity parent;
        public ScreenSlidePagerAdapter(VideoActivity videoActivity){
            parent = videoActivity;
        }

        @NonNull
        @Override
        public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//            return new VideoViewHolder(,apiController, feed.get(position));
            return new VideoViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.post_view, parent, false));
        }
        @Override
        public void onBindViewHolder(@NonNull VideoViewHolder holder, int position) {
            if (position == getItemCount()-1){

                Log.i("Swipe", "Loading more feed");
                parent.loadFeed();
            }
            FeedModel fm = feed.get(position);
            Call<UserModel> userCall = apiController.getVideoApi().getUserById(fm.getUserId());

            userCall.enqueue(new Callback<UserModel>() {
                @Override
                public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                    UserModel um = response.body();
                    fm.setUserName(um.getName());
                    holder.setVideo(new ObjetoMedia(fm.getVideoUri(apiController.getUrl()), fm.getLikes(), um.getName(), fm.getTitle()));
                }

                @Override
                public void onFailure(Call<UserModel> call, Throwable t) {

                }
            });
        }
        public void addFeed(FeedModel fm){
            feed.add(fm);
            notifyDataSetChanged();
        }
        @Override
        public int getItemCount() {
            return feed.size();
        }
    }
}