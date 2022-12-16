package com.lava.cricx.ui.component.players.infoFragments

import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.lava.cricx.R
import com.lava.cricx.data.Resource
import com.lava.cricx.databinding.FragmentStatsBinding
import com.lava.cricx.domain.model.players.Stats
import com.lava.cricx.ui.base.BaseFragment
import com.lava.cricx.ui.component.players.PlayerInfoViewModel
import com.lava.cricx.ui.component.players.adapter.stats.StatsAdapter
import com.lava.cricx.util.SingleEvent
import com.lava.cricx.util.extensions.gone
import com.lava.cricx.util.extensions.observe
import com.lava.cricx.util.extensions.showToast
import com.lava.cricx.util.extensions.visible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BattingFragment : BaseFragment<FragmentStatsBinding>(R.layout.fragment_stats) {

    private val viewModel: PlayerInfoViewModel by activityViewModels()
    lateinit var statsAdapter: StatsAdapter

    override fun initViewBinding(view: View): FragmentStatsBinding =
        FragmentStatsBinding.bind(view)

    override fun setupViews() {
        val layoutManager = LinearLayoutManager(context)
        binding.rvStats.layoutManager = layoutManager
    }

    private fun handleBattingStats(status: Resource<Stats>) {
        when (status) {
            is Resource.Loading -> showLoadingView()
            is Resource.Success -> bindBattingStatsData(status.data)
            is Resource.DataError -> {
                showDataView(false)
                status.errorCode?.let { viewModel.showToastMessage(it) }
            }
        }
    }

    private fun showDataView(show: Boolean) {
        binding.pbLoading.visibility = if (show) View.GONE else View.VISIBLE
        binding.clStatsHeader.root.visibility = if (show) View.VISIBLE else View.GONE
        binding.rvStats.visibility = if (show) View.VISIBLE else View.GONE
    }

    private fun showLoadingView() {
        binding.clStatsHeader.root.gone()
        binding.rvStats.gone()
        binding.pbLoading.visible()
    }

    private fun bindBattingStatsData(battingStats: Stats?) {
        if (!battingStats?.table.isNullOrEmpty() && !battingStats?.headers.isNullOrEmpty()) {
            val header = battingStats!!.headers
            setHeader(header)
            statsAdapter = StatsAdapter(battingStats.table)
            binding.rvStats.adapter = statsAdapter
            showDataView(true)
        } else {
            showDataView(false)
        }
    }

    private fun setHeader(header: List<String>) {
        binding.clStatsHeader.tvHeader.text = getString(R.string.batting)
        binding.clStatsHeader.tvHeaderValue1.text = header[1]
        binding.clStatsHeader.tvHeaderValue2.text = header[2]
        binding.clStatsHeader.tvHeaderValue3.text = header[3]
        binding.clStatsHeader.tvHeaderValue4.text = header[4]
    }

    private fun observeToast(event: LiveData<SingleEvent<Any>>) {
        binding.root.showToast(this, event, Toast.LENGTH_LONG)
    }

    override fun observeViewModel() {
        observe(liveData = viewModel.battingStats, action = ::handleBattingStats)
        observeToast(viewModel.showToast)
    }
}