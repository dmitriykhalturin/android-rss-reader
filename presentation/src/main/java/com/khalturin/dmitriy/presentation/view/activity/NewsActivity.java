package com.khalturin.dmitriy.presentation.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.khalturin.dmitriy.presentation.R;
import com.khalturin.dmitriy.presentation.model.NewsModel;
import com.khalturin.dmitriy.presentation.presenter.NewsPresenter;
import com.khalturin.dmitriy.presentation.view.NewsView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for android-rss-reader on 17.02.17 22:00.
 */

public class NewsActivity extends BaseActivity implements NewsView {

//==================================================================================================
//    Class Variables
//==================================================================================================

    public static final String NEWS_ID = "NEWS_ID";
    public static final long DEFAULT_NEWS_ID = -1;

    private long newsId;

    @BindView(R.id.toolbar)
    protected Toolbar toolbarView;

    @BindView(R.id.news_title)
    protected TextView titleView;

    @BindView(R.id.news_date)
    protected TextView dateView;

    @BindView(R.id.news_image)
    protected ImageView imageView;

    @BindView(R.id.news_description)
    protected TextView descriptionView;

//==================================================================================================
//    Class Constructor
//==================================================================================================

    public NewsActivity(){
        rssPresenter = new NewsPresenter(this);
    }

//==================================================================================================
//    Class Callbacks
//==================================================================================================

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        ButterKnife.bind(this);

        setSupportActionBar(toolbarView);

        ActionBar actionBar = getSupportActionBar();

        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        setNewsId();
    }

//==================================================================================================
//    Class Methods
//==================================================================================================

    private void setNewsId(){
        Intent intent = getIntent();
        newsId = intent.getLongExtra(NEWS_ID, DEFAULT_NEWS_ID);
    }

//==================================================================================================
//    Class Implementation NewsView
//==================================================================================================

    @Override
    public long getNewsId(){
        return newsId;
    }

    @Override
    public void setContent(NewsModel news){
        if(news != null){
            titleView.setText(news.getTitle());
            dateView.setText(news.getDate());
//            Picasso.with(this)
//                .load(news.getImage())
//                .into(imageView);
            descriptionView.setText(news.getDescription());
        }
    }

}