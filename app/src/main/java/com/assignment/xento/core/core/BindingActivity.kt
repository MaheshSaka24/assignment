package com.assignment.xento.core.core

import android.content.Intent
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.assignment.xento.R


abstract class BindingActivity<T : ViewDataBinding> : AppCompatActivity() {
    //  abstract fun getResourceLayout(): Int

    lateinit var binding: T

    @get:LayoutRes
    abstract var resourceIdRes: Int
    abstract var TAG: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES) //For day mode theme
        binding = DataBindingUtil.setContentView(this, resourceIdRes)
    }

    fun setCurrentScreen(screenName: String) {
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.left_in, R.anim.right_out)
    }

    override fun startActivity(intent: Intent?) {
        super.startActivity(intent)
        overridePendingTransition(R.anim.right_in, R.anim.left_out)
    }

    fun setToolbar(toolbar: Toolbar) {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

}