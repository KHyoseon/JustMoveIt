package com.example.justmoveit.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.List;

public class ImgUrl implements Serializable {
    private List<String> url;

    public ImgUrl(List<String> url){
        this.url = url;
    }

    protected ImgUrl(Parcel in) {
        url = in.createStringArrayList();
    }


}
