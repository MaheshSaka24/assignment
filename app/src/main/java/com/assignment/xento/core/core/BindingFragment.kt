package com.assignment.xento.core.core

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

/**
 * Binding Fragment using Databiding liabrary supprt
 * Author : Mahesh Saka
 */

abstract class BindingFragment<T : ViewDataBinding, F : Fragment> : Fragment() {

    @get:LayoutRes
    abstract var resourceIdRes: Int
    lateinit var binding: T

    //   private val firebaseAnalytics: FirebaseAnalytics by inject()
    private lateinit var fragment: F


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return DataBindingUtil.inflate<T>(inflater, resourceIdRes, container, false)
            .apply { binding = this }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setScreen()
    }

    private fun setScreen() {
        /* firebaseAnalytics.setCurrentScreen(
             this.activity!!,
             fragment.javaClass.simpleName,
             fragment.javaClass.simpleName
         )*/
    }


}