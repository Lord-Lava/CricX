package com.lava.cricx.ui.component.matches

import android.view.View
import com.lava.cricx.R
import com.lava.cricx.databinding.FragmentMatchesBinding
import com.lava.cricx.ui.base.BaseFragment

class MatchesFragment : BaseFragment<FragmentMatchesBinding>(R.layout.fragment_matches) {

    override fun initViewBinding(view: View): FragmentMatchesBinding =
        FragmentMatchesBinding.bind(view)

    override fun setupViews() {

    }

    override fun observeViewModel() {

    }
}