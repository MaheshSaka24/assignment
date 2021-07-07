package com.assignment.xento.ui.dashboard.books.activity

import android.os.Bundle
import coil.load
import com.assignment.xento.R
import com.assignment.xento.core.core.BindingActivity
import com.assignment.xento.databinding.ActivityBookDetailsBinding
import com.assignment.xento.model.books.BooksItem
import com.assignment.xento.ui.dashboard.books.fragment.BookFragment
import com.assignment.xento.ui.dashboard.news.fragment.NewsFragment

class BooksDetailActivity : BindingActivity<ActivityBookDetailsBinding>() {

    override var resourceIdRes: Int
        get() = R.layout.activity_book_details
        set(value) {}

    override var TAG: String
        get() = BooksDetailActivity::class.java.name
        set(value) {}

    private var booksItem: BooksItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setToolbar(binding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        if (intent.extras != null) {
            booksItem = intent.extras?.getSerializable(BookFragment.BOOK_DETAIL) as BooksItem
        }

        if (booksItem != null) {
            binding.imageViewBook.load(booksItem?.volumeInfo?.imageLinks?.smallThumbnail)
            binding.idTVpublisher.text = (booksItem?.volumeInfo?.publisher)
            binding.idTVNoOfPages.text = booksItem?.volumeInfo?.pageCount.toString()
            binding.idTVPublishDate.text =
                ("Published On : " + booksItem?.volumeInfo?.publishedDate)
            binding.idTVTitle.text = booksItem?.volumeInfo?.title
            binding.idTVDescription.text = booksItem?.volumeInfo?.description
        }

    }
}