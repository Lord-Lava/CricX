package com.lava.cricx.ui.component.news

import android.view.View
import com.lava.cricx.R
import com.lava.cricx.databinding.FragmentNewsBinding
import com.lava.cricx.ui.base.BaseFragment

class NewsFragment : BaseFragment<FragmentNewsBinding>(R.layout.fragment_news) {

    override fun initViewBinding(view: View): FragmentNewsBinding = FragmentNewsBinding.bind(view)

    override fun setupViews() {

    }

    override fun observeViewModel() {

    }
}