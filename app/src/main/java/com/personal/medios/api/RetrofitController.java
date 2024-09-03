package com.personal.medios.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitController {
    private final String url;

    VideoApi videoApi;
    public RetrofitController(String base_url){
        this.url = base_url;

        Retrofit r = new Retrofit.Builder()
                .baseUrl(this.url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        videoApi = r.create(VideoApi.class);

    }

    public VideoApi getVideoApi() {
        return videoApi;
    }
    public String getUrl() {
        return url;
    }
}
