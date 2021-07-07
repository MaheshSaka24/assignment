package com.assignment.xento.ui.dashboard.news.fragment

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
import com.assignment.xento.databinding.FragmentNewsBinding
import com.assignment.xento.model.news.Articles
import com.assignment.xento.other.Constants
import com.assignment.xento.other.Status
import com.assignment.xento.ui.dashboard.news.activity.NewsDetailActivity
import com.assignment.xento.ui.dashboard.news.adapter.NewsAdapter
import com.assignment.xento.ui.dashboard.viewmodel.DashBoardViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsFragment : BindingFragment<FragmentNewsBinding, NewsFragment>(),
    OnItemCallback<Articles> {

    override var resourceIdRes: Int
        get() = R.layout.fragment_news
        set(value) {}

    private val dashBoardViewModel: DashBoardViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.layoutSearchView.searchView.setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (!TextUtils.isEmpty(query?.trim())) {
                    callSearch(query!!)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (!TextUtils.isEmpty(newText?.trim())) {
                    callSearch(newText!!)
                }
                return true
            }

            fun callSearch(query: String) {
                getNewsDetails(query)
            }
        })
        getNewsDetails(Constants.DEFAULT_SEARCH_NEWS_CONSTANT)
    }

    private fun getNewsDetails(queryString: String) {
        dashBoardViewModel.fetchNews(queryString).observe(viewLifecycleOwner, {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        dashBoardViewModel.setProgressVisible(false)
                        if (it.data?.articles?.isNotEmpty() == true) {
                            val newsAdapter = NewsAdapter(this, it.data?.articles)
                            binding.recycleView.layoutManager =
                                requireActivity().getLinearVerticalLayoutManger()
                            binding.recycleView.adapter = newsAdapter
                        }
                    }
                    Status.ERROR -> {
                        dashBoardViewModel.setProgressVisible(false)

                    }
                    Status.LOADING -> {
                        dashBoardViewModel.setProgressVisible(true)
                    }
                }
            }
        })
    }

    override fun onItemClick(t: Articles, position: Int) {
        val intent = Intent(requireActivity(), NewsDetailActivity::class.java)
        val bundle = Bundle()
        bundle.putSerializable(NEWS_PARCEL,t)
        intent.putExtras(bundle)
        startActivity(intent)
    }

    companion object {
        fun newInstance(): NewsFragment {
            return NewsFragment()
        }
        const val NEWS_PARCEL = "newsparcel"
    }

}
