package com.lava.cricx.ui.component.home

import android.view.View
import com.lava.cricx.R
import com.lava.cricx.databinding.FragmentHomeBinding
import com.lava.cricx.ui.base.BaseFragment

class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {

    override fun initViewBinding(view: View): FragmentHomeBinding = FragmentHomeBinding.bind(view)

    override fun setupViews() {

    }

    override fun observeViewModel() {

    }

}