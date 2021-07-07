package com.assignment.xento.ui.dashboard.books.fragment

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.activityViewModels
import com.assignment.xento.R
import com.assignment.xento.core.core.BindingFragment
import com.assignment.xento.core.core.callbacks.OnItemCallback
import com.assignment.xento.core.core.utility.getLinearVerticalLayoutManger
import com.assignment.xento.databinding.FragmentBookBinding
import com.assignment.xento.model.books.BooksItem
import com.assignment.xento.other.Constants
import com.assignment.xento.other.Status
import com.assignment.xento.ui.dashboard.books.activity.BooksDetailActivity
import com.assignment.xento.ui.dashboard.books.adapter.BookAdapter
import com.assignment.xento.ui.dashboard.news.activity.NewsDetailActivity
import com.assignment.xento.ui.dashboard.news.fragment.NewsFragment
import com.assignment.xento.ui.dashboard.viewmodel.DashBoardViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BookFragment : BindingFragment<FragmentBookBinding, NewsFragment>(),
    OnItemCallback<BooksItem> {

    override var resourceIdRes: Int
        get() = R.layout.fragment_book
        set(value) {}

    private val dashBoardViewModel: DashBoardViewModel by activityViewModels()

    companion object {
        fun newInstance(): BookFragment {
            return BookFragment()
        }

        const val BOOK_DETAIL = "bookdetail"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.layoutSearchViewBook.searchView.setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (!TextUtils.isEmpty(query)) {
                    callSearch(query!!)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (!TextUtils.isEmpty(newText)) {
                    callSearch(newText!!)
                }
                return true
            }

            fun callSearch(query: String) {
                getBookDetail(query)
            }
        })
        dashBoardViewModel.setProgressVisible(true)
        getBookDetail(Constants.DEFAULT_SEARCH_BOOKS_CONSTANT)
    }


    private fun getBookDetail(queryString: String) {
        dashBoardViewModel.fetchBooks(queryString).observe(viewLifecycleOwner, {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        dashBoardViewModel.setProgressVisible(false)
                        if (it.data?.items?.isNotEmpty() == true) {
                            val booksAdapter = BookAdapter(this, it.data?.items)
                            binding.recycleView.layoutManager =
                                requireActivity().getLinearVerticalLayoutManger()
                            binding.recycleView.adapter = booksAdapter
                        }
                    }
                    Status.ERROR -> {
                        dashBoardViewModel.setProgressVisible(false)
                        // load error view here
                    }
                    Status.LOADING -> {
                        dashBoardViewModel.setProgressVisible(true)
                    }
                }
            }
        })
    }

    override fun onItemClick(t: BooksItem, position: Int) {
        val intent = Intent(requireActivity(), BooksDetailActivity::class.java)
        val bundle = Bundle()
        bundle.putSerializable(BOOK_DETAIL, t)
        intent.putExtras(bundle)
        startActivity(intent)
    }

}