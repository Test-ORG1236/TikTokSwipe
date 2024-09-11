package com.personal.medios.api.models;

import android.net.Uri;

import com.google.gson.annotations.Expose;

import java.util.List;

public class FeedModel extends MetaInfoModel {
    @Expose(deserialize = false)
    private String userName, audioFile;
    private String type;
    private int userId, audioTrackId, likes;
    private List<String> files;

    public String getAsString(){
        return "ID: "+getId()+", titulo: "+getTitle();
    }
    public String getAudioFile() {
        return audioFile;
    }

    public void setAudioFile(String audioFile) {
        this.audioFile = audioFile;
    }

    public int getAudioTrackId() {
        return audioTrackId;
    }

    public void setAudioTrackId(int audioTrackId) {
        this.audioTrackId = audioTrackId;
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
}
