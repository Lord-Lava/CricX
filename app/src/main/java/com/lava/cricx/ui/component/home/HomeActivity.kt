package com.lava.cricx.ui.component.home

import android.view.View
import android.widget.PopupMenu
import androidx.core.view.GravityCompat
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.lava.cricx.R
import com.lava.cricx.databinding.ActivityHomeBinding
import com.lava.cricx.databinding.SnippetToolbarBinding
import com.lava.cricx.ui.base.BaseActivity
import com.lava.cricx.util.extensions.visible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : BaseActivity<ActivityHomeBinding>() {

    private lateinit var navController: NavController
    private lateinit var toolbarBinding: SnippetToolbarBinding

    override fun initViewBinding(): ActivityHomeBinding =
        ActivityHomeBinding.inflate(layoutInflater)

    override fun setupViews() {
        toolbarBinding = SnippetToolbarBinding.bind(binding.root)
        navController = binding.navHostFragment.getFragment<NavHostFragment>().navController
        setupSmoothBottomMenu()
        setupAppBar(0)
        setupNavDrawer()
    }

    private fun setupNavDrawer() {
        binding.navDrawer.setNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.browsePlayers -> {
                    this.findNavController(R.id.navHostFragment).navigate(HomeFragmentDirections.actionHomeFragment2ToPlayersSearchFragment())
                }
            }
            binding.drawerLayout.close()
            false
        }
    }

    private fun setupAppBar(position: Int) {
        toolbarBinding.ivBackArrow.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
            setupAppBar(binding.bottomBar.itemActiveIndex)
        }
        toolbarBinding.ivNavDrawer.setOnClickListener {
            binding.drawerLayout.openDrawer(GravityCompat.START, true)
        }
        toolbarBinding.tvToolbarTitle.text = when (position) {
            0 -> getString(R.string.home)
            1 -> getString(R.string.news)
            2 -> getString(R.string.matches)
            3 -> getString(R.string.profile)
            else -> ""
        }
        toolbarBinding.ivBackArrow.visibility = when (position) {
            0 -> View.INVISIBLE
            else -> View.VISIBLE
        }
        toolbarBinding.ivNavDrawer.visibility = when (position) {
            0 -> View.VISIBLE
            else -> View.INVISIBLE
        }

    }

    private fun setupSmoothBottomMenu() {
        val popupMenu = PopupMenu(this, null)
        popupMenu.inflate(R.menu.menu_bottom_navbar)
        val menu = popupMenu.menu
        binding.bottomBar.setupWithNavController(menu, navController)
        binding.bottomBar.onItemSelected = { position -> setupAppBar(position) }
    }

    override fun observeViewModel() {}
}