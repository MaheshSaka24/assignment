package com.assignment.xento.ui.dashboard.news.activity

import android.os.Bundle
import coil.load
import com.assignment.xento.R
import com.assignment.xento.core.core.BindingActivity
import com.assignment.xento.databinding.ActivityNewsDetailsBinding
import com.assignment.xento.model.news.Articles
import com.assignment.xento.ui.dashboard.news.fragment.NewsFragment.Companion.NEWS_PARCEL

class NewsDetailActivity : BindingActivity<ActivityNewsDetailsBinding>() {

    override var resourceIdRes: Int
        get() = R.layout.activity_news_details
        set(value) {}

    override var TAG: String
        get() = NewsDetailActivity::class.java.name
        set(value) {}

    private var articles: Articles? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setToolbar(binding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        if (intent.extras != null) {
            articles = intent.extras?.getSerializable(NEWS_PARCEL) as Articles
        }
        if (articles != null) {
            binding.imageViewNews.load(articles?.urlToImage)
            binding.txtViewTitle.text = articles?.title
            binding.txtViewDescription.text = articles?.description
        }
    }
}