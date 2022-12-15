package com.lava.cricx.ui.component.players.infoFragments

import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.lava.cricx.R
import com.lava.cricx.data.Resource
import com.lava.cricx.databinding.FragmentStatsBinding
import com.lava.cricx.domain.model.players.Stats
import com.lava.cricx.ui.base.BaseFragment
import com.lava.cricx.ui.component.players.PlayerInfoViewModel
import com.lava.cricx.ui.component.players.adapter.stats.StatsAdapter
import com.lava.cricx.util.extensions.gone
import com.lava.cricx.util.extensions.observe
import com.lava.cricx.util.extensions.visible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BowlingFragment : BaseFragment<FragmentStatsBinding>(R.layout.fragment_stats) {

    private val viewModel: PlayerInfoViewModel by activityViewModels()
    lateinit var statsAdapter: StatsAdapter

    override fun initViewBinding(view: View): FragmentStatsBinding =
        FragmentStatsBinding.bind(view)

    override fun setupViews() {
        val layoutManager = LinearLayoutManager(context)
        binding.rvStats.layoutManager = layoutManager
    }

    private fun handleBowlingStats(status: Resource<Stats>) {
        when (status) {
            is Resource.Loading -> showLoadingView()
            is Resource.Success -> bindBowlingStatsData(status.data)
            is Resource.DataError -> {
                showDataView(false)
                status.errorCode?.let { viewModel.showToastMessage(it) }
            }
        }
    }

    private fun showDataView(show: Boolean) {
        binding.pbLoading.visibility = if (show) View.GONE else View.VISIBLE
        binding.rvStats.visibility = if (show) View.VISIBLE else View.GONE
    }

    private fun showLoadingView() {
        binding.rvStats.gone()
        binding.pbLoading.visible()
    }

    private fun bindBowlingStatsData(bowlingStats: Stats?) {
        if (!bowlingStats?.table.isNullOrEmpty() && !bowlingStats?.headers.isNullOrEmpty()) {
            val header = bowlingStats!!.headers
            setHeader(header)
            statsAdapter = StatsAdapter(bowlingStats.table)
            binding.rvStats.adapter = statsAdapter
            showDataView(true)
        } else {
            showDataView(false)
        }
    }

    private fun setHeader(header: List<String>) {
        binding.clStatsHeader.tvHeader.text = getString(R.string.bowling)
        binding.clStatsHeader.tvHeaderValue1.text = header[1]
        binding.clStatsHeader.tvHeaderValue2.text = header[2]
        binding.clStatsHeader.tvHeaderValue3.text = header[3]
        binding.clStatsHeader.tvHeaderValue4.text = header[4]
    }

    override fun observeViewModel() {
        observe(liveData = viewModel.bowlingStats, action = ::handleBowlingStats)
    }
}