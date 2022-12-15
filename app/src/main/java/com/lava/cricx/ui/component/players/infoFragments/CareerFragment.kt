package com.lava.cricx.ui.component.players.infoFragments

import android.view.View
import androidx.fragment.app.activityViewModels
import com.lava.cricx.R
import com.lava.cricx.data.Resource
import com.lava.cricx.databinding.FragmentCareerBinding
import com.lava.cricx.domain.model.players.PlayerCareer
import com.lava.cricx.ui.base.BaseFragment
import com.lava.cricx.ui.component.players.PlayerInfoViewModel
import com.lava.cricx.util.extensions.observe
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CareerFragment : BaseFragment<FragmentCareerBinding>(R.layout.fragment_career) {

    private val viewModel: PlayerInfoViewModel by activityViewModels()

    override fun initViewBinding(view: View): FragmentCareerBinding =
        FragmentCareerBinding.bind(view)

    override fun setupViews() {

    }

    private fun handlePlayerCareer(status: Resource<PlayerCareer>) {
        when (status) {
            is Resource.Loading -> {

            }
            is Resource.Success -> {

            }
            is Resource.DataError -> {

            }
        }
    }

    override fun observeViewModel() {
        observe(liveData = viewModel.playerCareer, ::handlePlayerCareer)
    }

}