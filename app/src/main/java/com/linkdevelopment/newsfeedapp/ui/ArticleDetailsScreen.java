package com.linkdevelopment.newsfeedapp.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.linkdevelopment.newsfeedapp.Data.Model.Article;
import com.linkdevelopment.newsfeedapp.R;
import com.linkdevelopment.newsfeedapp.Utils.Utils;
import com.squareup.picasso.Picasso;

public class ArticleDetailsScreen extends AppCompatActivity {

    ImageView backButton, articleImage;
    TextView openButton, articlePublishDate, articleTitle, articleAuthor, articleDescription;

    Article selectedArticle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_details_screen);



        selectedArticle = (Article) getIntent().getSerializableExtra("selectedArticle");


        backButton = findViewById(R.id.details_back_btn);
        articleImage = findViewById(R.id.details_article_image);
        openButton = findViewById(R.id.details_open_btn);
        articlePublishDate = findViewById(R.id.details_article_publish_date);
        articleTitle = findViewById(R.id.details_article_title);
        articleAuthor = findViewById(R.id.details_article_author);
        articleDescription = findViewById(R.id.details_article_description);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArticleDetailsScreen.super.onBackPressed();
            }
        });

        if (!TextUtils.isEmpty(selectedArticle.getUrlToImage())) {
            Picasso.with(getApplicationContext()).load(selectedArticle.getUrlToImage())
                    .error(R.drawable.placeholder)
                    .placeholder(R.drawable.placeholder)
                    .into(articleImage);
        }

        articleTitle.setText(selectedArticle.getTitle());

        articleAuthor.setText("By " + selectedArticle.getAuthor());

        articlePublishDate.setText(Utils.changeUTCToLocaleTime(selectedArticle.getPublishedAt()));

        articleDescription.setText((selectedArticle.getDescription()));

        openButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(selectedArticle.getUrl()));
                startActivity(i);
            }
        });


    }
}