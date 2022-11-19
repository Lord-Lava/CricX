package com.lava.cricx.ui.component.profile

import android.view.View
import com.lava.cricx.R
import com.lava.cricx.databinding.FragmentProfileBinding
import com.lava.cricx.ui.base.BaseFragment

class ProfileFragment : BaseFragment<FragmentProfileBinding>(R.layout.fragment_profile) {

    override fun initViewBinding(view: View): FragmentProfileBinding =
        FragmentProfileBinding.bind(view)

    override fun setupViews() {

    }

    override fun observeViewModel() {

    }
}