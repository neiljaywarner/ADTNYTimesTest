package com.neiljaywarner.adtnytimestest.ui;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.neiljaywarner.adtnytimestest.R;
import com.neiljaywarner.adtnytimestest.model.ArticlesList;
import com.neiljaywarner.adtnytimestest.model.Article;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class ArticlesRecyclerViewAdapter extends RecyclerView.Adapter<ArticlesRecyclerViewAdapter.ArticleViewHolder> {

    private List<Article> articles = new ArrayList<>();


    public void setData(ArticlesList articlesList) {
        if (articles != null) {
            articles = articlesList.getResults();
            this.notifyDataSetChanged();
        }
    }

    @Override
    public ArticleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.article_item, parent, false);

        return new ArticleViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(ArticleViewHolder holder, int position) {
        final Article article = articles.get(position);

        holder.textViewTitle.setText(article.getTitle());

        if (article.getThumbnailUrl().length() > 0) {
            Picasso.with(holder.imageViewThumbnail.getContext()).load(article.getThumbnailUrl()).into(holder.imageViewThumbnail);
        }
    }


    @Override
    public int getItemCount() {
        return articles.size();
    }

    public static class ArticleViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewTitle;
        public ImageView imageViewThumbnail;

        public ArticleViewHolder(View itemView) {
            super(itemView);
            //TODO: Consider Butterknife, saves boilerplate.
            textViewTitle = (TextView) itemView.findViewById(R.id.textViewTitle);
            imageViewThumbnail = (ImageView) itemView.findViewById(R.id.imageViewThumbnail);

        }
    }

}
