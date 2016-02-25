package com.neiljaywarner.adtnytimestest;

import android.util.Log;

import com.neiljaywarner.adtnytimestest.model.ArticlesList;

import retrofit.RestAdapter;
import retrofit.http.GET;
import rx.Observable;


public class NYTArticlesService {

    private static NYTArticlesService sInstance = new NYTArticlesService();

    private static String sApiKey = NYTArticlesEndPoint.APIKey;

    private ArticlesServcie mArticlesService;

    private NYTArticlesService() {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint("http://api.nytimes.com")
                .build();

        mArticlesService = restAdapter.create(ArticlesServcie.class);


    }

    public static NYTArticlesService getInstance() {
        return sInstance;
    }

    public Observable<ArticlesList> getArticles() {
        Log.i("NJW", "get Observable in NYTArticlesService");

        return mArticlesService.getArticles();
    }

    /**
     * http://api.nytimes.com/svc/topstories/v1/home.json?api-key=15629235341919a7b4b8b6e9344f9bca:6:72095783
     */
    public interface ArticlesServcie {

        @GET("/svc/topstories/v1/home.json?api-key=15629235341919a7b4b8b6e9344f9bca:6:72095783")
        Observable<ArticlesList> getArticles();
    }


}
