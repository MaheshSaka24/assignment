package com.assignment.xento.ui.dashboard.news.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.assignment.xento.core.core.BindingViewHolder
import com.assignment.xento.core.core.callbacks.OnItemCallback
import com.assignment.xento.core.core.utility.formatDate
import com.assignment.xento.core.core.utility.getLayoutInflater
import com.assignment.xento.databinding.LayoutViewNewsItemBinding
import com.assignment.xento.model.news.Articles

class NewsAdapter(
    val onItemCallback: OnItemCallback<Articles>,
    val newsItemList: List<Articles>
) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    inner class NewsViewHolder(view: View) : BindingViewHolder<LayoutViewNewsItemBinding>(view) {
        init {
            binding.root.setOnClickListener {
                onItemCallback.onItemClick(newsItemList[layoutPosition], layoutPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view =
            LayoutViewNewsItemBinding.inflate(
                parent.context.getLayoutInflater()!!,
                parent,
                false
            ).root
        return NewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val item = newsItemList[position]
        holder.binding.imageViewNews.load(item.urlToImage)
        holder.binding.txtViewTitle.text = item.title
        holder.binding.txtViewDesc.text = item?.description
        holder.binding.txtViewAuthor.text = item?.author
        holder.binding.txtViewPublishDate.text = item?.publishedAt.formatDate()
        holder.binding.source.text = item?.source.name?:""
    }

    override fun getItemCount(): Int {
        return newsItemList.size
    }
}