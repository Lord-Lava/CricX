package com.lava.cricx.ui.component.players

import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import com.google.android.material.tabs.TabLayoutMediator
import com.lava.cricx.R
import com.lava.cricx.databinding.ActivityPlayerInfoBinding
import com.lava.cricx.databinding.SnippetToolbarBinding
import com.lava.cricx.ui.base.BaseActivity
import com.lava.cricx.ui.component.players.adapter.ViewPagerAdapter
import com.lava.cricx.util.extensions.showToast
import com.lava.cricx.util.transformers.FanTransformation
import com.lava.cricx.util.transformers.GateTransformation
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PlayerInfoActivity : BaseActivity<ActivityPlayerInfoBinding>() {

    private val viewModel: PlayerInfoViewModel by viewModels()
    private val titles = arrayOf("info", "batting", "balling", "career")
    private lateinit var toolbarBinding: SnippetToolbarBinding

    override fun initViewBinding(): ActivityPlayerInfoBinding =
        ActivityPlayerInfoBinding.inflate(layoutInflater)

    override fun setupViews() {
        binding.vpPlayerInfo.adapter = ViewPagerAdapter(this, titles)
        binding.vpPlayerInfo.setPageTransformer(FanTransformation())
        TabLayoutMediator(binding.tlInfoTabs, binding.vpPlayerInfo) { tab, position ->
            tab.text = titles[position]
        }.attach()
        toolbarBinding = SnippetToolbarBinding.bind(binding.root)
        setupAppBar()
        viewModel.playerId?.let {
            viewModel.getPlayerInfo(it)
            viewModel.getBattingStats(it)
            viewModel.getBowlingStats(it)
            viewModel.getPlayerCareer(it)
        }
    }

    private fun setupAppBar() {
        toolbarBinding.ivBackArrow.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
        toolbarBinding.tvToolbarTitle.text = getString(R.string.player_info)
        toolbarBinding.ivNavDrawer.setImageDrawable(ContextCompat.getDrawable(this,
            R.drawable.ic_share))
    }

    override fun observeViewModel() {

    }
}