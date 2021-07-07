package com.assignment.xento.core.core

import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

/**
 * Binding ViewHolder using Databiding liabrary supprt
 * Author : Mahesh Saka
 */

abstract class BindingViewHolder<T : ViewDataBinding>(view: View) : RecyclerView.ViewHolder(view) {
    val binding: T = DataBindingUtil.bind(view)!!
}