package com.personal.medios.api.models;

import android.net.Uri;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class MetaInfoModel {
    private int id;
    private String file, title, baseUrl;

    public static final List<String> types = Collections.unmodifiableList(
            new ArrayList<String>(){{
                add("video");
                add("image");
                add("image_sequence");
            }}
    );

    public abstract Uri getUri();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }
}
