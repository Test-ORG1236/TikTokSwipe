package com.personal.medios.api.models;

import android.net.Uri;

public class AudioModel extends MetaInfoModel{
    @Override
    public Uri getUri()  {
        return Uri.parse(getBaseUrl()+"/audio/"+getFile());
    }

}
