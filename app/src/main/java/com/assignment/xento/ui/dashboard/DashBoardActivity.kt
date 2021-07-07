package com.assignment.xento.ui.dashboard

import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import com.assignment.xento.R
import com.assignment.xento.core.core.BindingActivity
import com.assignment.xento.core.core.utility.hideView
import com.assignment.xento.core.core.utility.showView
import com.assignment.xento.databinding.ActivityDashboardBinding
import com.assignment.xento.ui.dashboard.books.fragment.BookFragment
import com.assignment.xento.ui.dashboard.news.fragment.NewsFragment
import com.assignment.xento.ui.dashboard.viewmodel.DashBoardViewModel
import com.assignment.xento.ui.main.SectionsPagerAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashBoardActivity : BindingActivity<ActivityDashboardBinding>() {

    private val dashBoardViewModel: DashBoardViewModel by viewModels()
    private val fragmentList = ArrayList<Fragment>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        fragmentList.add(NewsFragment.newInstance())
        fragmentList.add(BookFragment.newInstance())

        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager, fragmentList)
        binding.viewPager.apply {
            adapter = sectionsPagerAdapter
            binding.tabs.setupWithViewPager(this)
        }

        dashBoardViewModel.showProgrssBar.observe(this, {
            if (it)
                binding.layoutProgress.showView()
            else
                binding.layoutProgress.hideView()
        })
    }

    override var resourceIdRes: Int
        get() = R.layout.activity_dashboard
        set(value) {}

    override var TAG: String
        get() = DashBoardActivity::class.java.name
        set(value) {}
}