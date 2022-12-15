package com.lava.cricx.ui.component.players.infoFragments

import android.view.View
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LiveData
import com.bumptech.glide.Glide
import com.lava.cricx.R
import com.lava.cricx.data.Resource
import com.lava.cricx.data.dto.mapper.toPlayerInfo
import com.lava.cricx.data.dto.players.info.PlayerInfoDto
import com.lava.cricx.databinding.FragmentInfoBinding
import com.lava.cricx.domain.model.players.PlayerInfo
import com.lava.cricx.ui.base.BaseFragment
import com.lava.cricx.ui.component.players.PlayerInfoViewModel
import com.lava.cricx.util.SingleEvent
import com.lava.cricx.util.extensions.gone
import com.lava.cricx.util.extensions.observe
import com.lava.cricx.util.extensions.showToast
import com.lava.cricx.util.extensions.visible
import com.lava.cricx.util.glideUrlBuilder
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InfoFragment : BaseFragment<FragmentInfoBinding>(R.layout.fragment_info) {

    private val viewModel: PlayerInfoViewModel by activityViewModels()

    override fun initViewBinding(view: View): FragmentInfoBinding = FragmentInfoBinding.bind(view)

    override fun setupViews() {

    }

    private fun handlePlayerInfo(status: Resource<PlayerInfo>) {
        when (status) {
            is Resource.Loading -> showLoadingView()
            is Resource.Success -> {
                bindPlayerInfoData(playerInfo = status.data)
            }
            is Resource.DataError -> {
                showDataView(false)
                status.errorCode?.let { viewModel.showToastMessage(it) }
            }
        }
    }

    private fun bindPlayerInfoData(playerInfo: PlayerInfo?) {
        if (playerInfo != null) {
            Glide.with(binding.ivPlayer)
                .load(glideUrlBuilder(playerInfo.faceImageId))
                .placeholder(R.drawable.ic_user_rect)
                .error(R.drawable.ic_user_rect)
                .into(binding.ivPlayer)
            binding.tvPlayerName.text = playerInfo.name
            binding.tvPlayerTeam.text = playerInfo.teams[0]
            binding.tvPlayerRole.text = playerInfo.role
            binding.tvDob.text = playerInfo.dob
            binding.tvBirthPlaceInfo.text = playerInfo.birthPlace
            binding.tvHeightInfo.text = playerInfo.height
            binding.tvBatTest.text = playerInfo.battingStats.testBestRank
            binding.tvBatOdi.text = playerInfo.battingStats.odiBestRank
            binding.tvBatT20.text = playerInfo.battingStats.t20BestRank
            binding.tvBallTest.text = playerInfo.bowlingStats.testBestRank
            binding.tvBallOdi.text = playerInfo.bowlingStats.odiBestRank
            binding.tvBallT20.text = playerInfo.bowlingStats.t20BestRank
            binding.tvBioInfo.text = playerInfo.bio
            showDataView(true)
        } else {
            showDataView(false)
        }
    }

    private fun showDataView(show: Boolean) {
        binding.pbLoading.visibility = if (show) View.GONE else View.VISIBLE
        binding.svInfo.visibility = if (show) View.VISIBLE else View.GONE
    }

    private fun showLoadingView() {
        binding.pbLoading.visible()
        binding.svInfo.gone()
    }

    private fun observeToast(event: LiveData<SingleEvent<Any>>) {
        binding.root.showToast(this, event, Toast.LENGTH_LONG)
    }

    override fun observeViewModel() {
        observe(liveData = viewModel.playerInfo, action = ::handlePlayerInfo)
        observeToast(viewModel.showToast)
    }
}