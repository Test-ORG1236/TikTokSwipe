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

import com.personal.medios.api.models.FeedModel;
import com.personal.medios.api.models.FeedPageModel;
import com.personal.medios.api.RetrofitController;
import com.personal.medios.api.models.UserModel;
import com.personal.medios.post.PostHolder;
import com.personal.medios.post.PostHolderFactory;
import com.personal.medios.post.PostHolderFactoryManager;
import com.personal.medios.post.PostHolderObservable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

 public class VideoActivity extends FragmentActivity {

     private String nextCursor = null;
     private boolean usedCursor = false;
     private ScreenSlidePagerAdapter pagerAdapter;
     static private RetrofitController apiController;
     private PostHolderObservable postsObservable;
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

        postsObservable = new PostHolderObservable();
        Intent i = getIntent();

        String ApiUrl = (String) Objects.requireNonNull(i.getExtras()).get("API_URL");
        apiController = new RetrofitController(ApiUrl);
        ViewPager2 viewPager = findViewById(R.id.swipe_container);
        pagerAdapter = new ScreenSlidePagerAdapter(this, postsObservable, apiController);
        viewPager.setAdapter(pagerAdapter);
        loadFeed();

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            private int myState;
            private int currentPosition, previousPosition;
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (myState == ViewPager2.SCROLL_STATE_SETTLING && position==currentPosition) {
                    Log.i("Swipe", "Settling: " + position + ", " + currentPosition);
                    postsObservable.notifyVisible(currentPosition);
                    postsObservable.notifySwipedFrom(previousPosition);
                    if (position==pagerAdapter.getItemCount()-1) loadFeed();
                }
//                if (myState == ViewPager2.SCROLL_STATE_DRAGGING) {
//                    Log.i("Swipe", "Dragging: " + position + ", " + currentPosition);
//                }
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                previousPosition = currentPosition;
                currentPosition = position;
                super.onPageSelected(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                myState = state;
                super.onPageScrollStateChanged(state);
            }
        });
    }

    public void loadFeed(){
        Call<FeedPageModel> callFeedPage;
        if (!usedCursor) {
            if (nextCursor == null) callFeedPage = apiController.getVideoApi().getFeed();
            else {
                callFeedPage = apiController.getVideoApi().getFeed(nextCursor);
                usedCursor = true;
            }

            callFeedPage.enqueue(new Callback<FeedPageModel>() {
                @Override
                public void onResponse(Call<FeedPageModel> call, Response<FeedPageModel> response) {
                    FeedPageModel b = response.body();
                    nextCursor = b.getCursor_token();

                    usedCursor = false;
                    for (FeedModel fm : b.getFeed()) {
                        fm.setBaseUrl(apiController.getUrl());
                        pagerAdapter.addFeed(fm);
                    }
                    pagerAdapter.notifyItemRangeInserted(pagerAdapter.getItemCount()-2,pagerAdapter.getItemCount());
                }

                @Override
                public void onFailure(Call<FeedPageModel> call, Throwable t) {

                }
            });
        }
    }
    static private class ScreenSlidePagerAdapter extends RecyclerView.Adapter<PostHolder> {

        List<FeedModel> feed = new ArrayList<>();
        VideoActivity parent;
        private boolean initialized;
        private PostHolderObservable postsObservable;
        RetrofitController retrofitController;
        public ScreenSlidePagerAdapter(VideoActivity videoActivity, PostHolderObservable o, RetrofitController retrofitController){
            parent = videoActivity;
            postsObservable = o;
            this.retrofitController = retrofitController;
        }

        @NonNull
        @Override
        public PostHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//            return new VideoViewHolder(,apiController, feed.get(position));
            Log.i("Swipe", "New holder with view type: "+ viewType);
            PostHolderFactory factory = PostHolderFactoryManager.getFactory(viewType);
            return factory.createPostHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.post_view, parent, false), postsObservable, retrofitController);
        }


        @Override
        public int getItemViewType(int position) {

            FeedModel fm = feed.get(position);
            return FeedModel.types.indexOf(fm.getType());
        }

        @Override
        public void onBindViewHolder(@NonNull PostHolder holder, int position) {

            FeedModel fm = feed.get(position);

            Call<UserModel> userCall = apiController.getVideoApi().getUserById(fm.getUserId());

            userCall.enqueue(new Callback<UserModel>() {
                @Override
                public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                    UserModel um = response.body();
                    fm.setUserName(um.getName());
                    holder.setInfo(fm);
                    if (!initialized){
                        holder.NotifySwipedTo();
                        initialized = true;
                    }
                }

                @Override
                public void onFailure(Call<UserModel> call, Throwable t) {

                }
            });
        }
        public void addFeed(FeedModel fm){
            feed.add(fm);
        }
        @Override
        public int getItemCount() {
            return feed.size();
        }
    }
}