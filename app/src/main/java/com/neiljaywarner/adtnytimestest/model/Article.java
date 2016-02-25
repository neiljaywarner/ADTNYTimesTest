package com.neiljaywarner.adtnytimestest.model;

import android.text.TextUtils;

import com.google.gson.Gson;

/**
 * Created by neil on 9/10/15.
 */
public class Article {

    private String title;
    private String multimedia;  //from JSON

    public String getTitle() {
        return title;
    }
    public String getThumbnailUrl() {

        if (TextUtils.isEmpty(multimedia)) {
            return "";
        } else {
            Gson gson = new Gson();
            MultimediaItem[] arr = gson.fromJson(multimedia, MultimediaItem[].class);
            return arr[0].getUrl();
        }
    }


    // multimedia as a json arraylist
    // contains OPTIONAL multimedia array with list of tiems

}
