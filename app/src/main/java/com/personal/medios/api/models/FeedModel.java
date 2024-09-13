package com.personal.medios.api.models;

import android.net.Uri;

import com.google.gson.annotations.Expose;

import java.util.List;

public class FeedModel extends MetaInfoModel {
    @Expose(deserialize = false)
    private String userName;
    private String type;
    private int userId, likes;
    private List<String> files;
    private AudioModel audio;
    private boolean isPresentable;

    public boolean isPresentable() {
        return isPresentable;
    }

    public void setPresentable(boolean presentable) {
        isPresentable = presentable;
    }

    public String getAsString(){
        return "ID: "+getId()+", titulo: "+getTitle();
    }

    public AudioModel getAudio() {
        return audio;
    }

    public void setAudio(AudioModel audio) {
        this.audio = audio;
    }

    public List<String> getFiles() {
        return files;
    }

    public void setFiles(List<String> files) {
        this.files = files;
    }

    public String getFile(int index){
        if (files==null) return null;
        return files.get(index);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }
    private Uri getUri(String url, String fileName){
        if (fileName!=null){
            return Uri.parse(url+"/post-content/"+userName+"/"+fileName);
        }
        return null;
    }
    @Override
    public Uri getUri() {
        return getUri(getBaseUrl(), getFile());
    }

    public Uri getUri(int index) {
        return getUri(getBaseUrl(), getFile(index));
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public void setBaseUrl(String baseUrl) {
        super.setBaseUrl(baseUrl);
        if (audio != null) audio.setBaseUrl(baseUrl);
    }
}
