package com.linkdevelopment.newsfeedapp.ui.explore;

import android.content.Context;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.linkdevelopment.newsfeedapp.Data.Model.Article;
import com.linkdevelopment.newsfeedapp.R;
import com.linkdevelopment.newsfeedapp.Utils.Utils;
import com.squareup.picasso.Picasso;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class ArticleRecyclerViewAdapter extends RecyclerView.Adapter<ArticleRecyclerViewAdapter.CustomViewHolder> {

    private List<Article> articlesList;
    private Context mContext;
    private final View.OnClickListener mOnClickListener = new ExploreFragment.MyOnClickListener();

    public ArticleRecyclerViewAdapter(List<Article> articlesList, Context mContext) {
        this.articlesList = articlesList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.article_single_item, null);
        view.setOnClickListener(mOnClickListener);
        CustomViewHolder viewHolder = new CustomViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        Article article = articlesList.get(position);

        if (!TextUtils.isEmpty(article.getUrlToImage())) {
            Picasso.with(mContext).load(article.getUrlToImage())
                    .error(R.drawable.placeholder)
                    .placeholder(R.drawable.placeholder)
                    .into(holder.articleImage);
        }

        holder.articleTitle.setText(article.getTitle());

        holder.articleAuthor.setText("By " + article.getAuthor());

        holder.articlePublishedDate.setText(Utils.changeUTCToLocaleTime(article.getPublishedAt()));

    }

    @Override
    public int getItemCount() {
        return (null != articlesList ? articlesList.size() : 0);
    }


    class CustomViewHolder extends RecyclerView.ViewHolder {
        protected ImageView articleImage;
        protected TextView articleTitle;
        protected TextView articleAuthor;
        protected TextView articlePublishedDate;

        public CustomViewHolder(View view) {
            super(view);
            this.articleImage = (ImageView) view.findViewById(R.id.article_image);
            this.articleTitle = (TextView) view.findViewById(R.id.article_title);
            this.articleAuthor = (TextView) view.findViewById(R.id.article_author);
            this.articlePublishedDate = (TextView) view.findViewById(R.id.article_publish_date);
        }
    }


}



