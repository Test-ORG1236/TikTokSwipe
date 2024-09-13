package com.personal.medios.api;

import com.personal.medios.api.models.AudioModel;
import com.personal.medios.api.models.FeedModel;
import com.personal.medios.api.models.FeedPageModel;
import com.personal.medios.api.models.UserModel;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface VideoApi {
    @GET("feed")
    Call<FeedPageModel> getFeed();

    @GET("feed")
    Call<FeedPageModel> getFeed(@Query("cursor_token") String cursor_token);

    @GET("users")
    Call<List<UserModel>> getUsers();

    @GET("users/{userId}")
    Call<UserModel> getUserById(@Path(value = "userId", encoded = true) int userId);


    @POST("like/{audioId}")
    Call<Void> likeVideo(@Path(value="audioId", encoded = true) int audioId);
}
