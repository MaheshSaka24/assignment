package com.assignment.xento.ui.dashboard.books.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.assignment.xento.core.core.BindingViewHolder
import com.assignment.xento.core.core.callbacks.OnItemCallback
import com.assignment.xento.core.core.utility.getLayoutInflater
import com.assignment.xento.databinding.LayoutViewBooksItemBinding
import com.assignment.xento.model.books.BooksItem

class BookAdapter(
    val onItemCallback: OnItemCallback<BooksItem>,
    val booksItemList: List<BooksItem>
) : RecyclerView.Adapter<BookAdapter.BookViewHolder>() {

    inner class BookViewHolder(view: View) : BindingViewHolder<LayoutViewBooksItemBinding>(view) {
        init {
            binding.root.setOnClickListener {
                onItemCallback.onItemClick(booksItemList[layoutPosition], layoutPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val view =
            LayoutViewBooksItemBinding.inflate(
                parent.context.getLayoutInflater()!!,
                parent,
                false
            ).root
        return BookViewHolder(view)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val item = booksItemList[position]
        holder.binding.imageViewBook.load(item?.volumeInfo?.imageLinks?.thumbnail)
        holder.binding.txtViewBookTitle.text = item?.volumeInfo?.title
        holder.binding.txtViewBookPublisher.text = item?.volumeInfo?.publisher
        holder.binding.txtViewPageCount.text = item?.volumeInfo?.pageCount.toString()
        holder.binding.txtViewDate.text = item?.volumeInfo?.publishedDate
    }

    override fun getItemCount(): Int {
        return booksItemList.size
    }
}