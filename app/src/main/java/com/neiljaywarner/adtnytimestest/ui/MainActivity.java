package com.neiljaywarner.adtnytimestest.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.neiljaywarner.adtnytimestest.NYTArticlesService;
import com.neiljaywarner.adtnytimestest.R;
import com.neiljaywarner.adtnytimestest.model.ArticlesList;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Dialog themed activity to go on top when the user presses 8/10 or similar.
 */
public class MainActivity extends AppCompatActivity {


   // private static final String TAG = MainActivity.class.getSimpleName();
    private static final String TAG = "NJW";

    private ArticlesList mArticlesList;

    private CompositeSubscription mCompositeSubscription;
    private ArticlesRecyclerViewAdapter mAdapter;
    private View mEmptyView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCompositeSubscription = new CompositeSubscription();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getApplicationContext()));
        mAdapter = new ArticlesRecyclerViewAdapter();

        mCompositeSubscription = new CompositeSubscription();

        recyclerView.setAdapter(mAdapter);

        updateArticleList();

        mEmptyView = findViewById(android.R.id.empty);

    }

    private void updateArticleList() {
        Log.i("NJW", "In updateArticleList");
        final NYTArticlesService articlesService = NYTArticlesService.getInstance();

        final Observable<ArticlesList> articlesListObservable;

        articlesListObservable = articlesService.getArticles();
        //TODO: If no internet access pop snackbar and ask them to check their connetion in advance.
        if (articlesListObservable == null) {
            Log.i(TAG, "retrofit observable=null");
            return;
        }
        mCompositeSubscription.add(articlesListObservable
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<ArticlesList>() {
                            @Override
                            public void onCompleted() {
                                Log.i(TAG, " observable completed.");
                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.i(TAG, " observable error;" + e.getMessage());
                                Toast.makeText(getApplicationContext(), "..Please check internet connection and refresh.", Toast.LENGTH_LONG).show();
                                //TODO: Strings.xml strings.
                            }

                            @Override
                            public void onNext(ArticlesList videosList) {
                                mArticlesList = videosList;
                                mAdapter.setData(mArticlesList);

                                if (mArticlesList.getResults().size() == 0) {
                                    mEmptyView.setVisibility(View.VISIBLE);
                                } else {
                                    mEmptyView.setVisibility(View.GONE);
                                }
                            }
                        })
        );

        //NOTE: I used rx and I've used loaders and services and even asynctasks+eventbus, i'm certainly fine with team decision

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mCompositeSubscription.unsubscribe();
    }
}
