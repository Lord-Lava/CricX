package com.lava.cricx.ui.component.home

import android.widget.PopupMenu
import androidx.core.view.GravityCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.lava.cricx.R
import com.lava.cricx.databinding.ActivityHomeBinding
import com.lava.cricx.databinding.SnippetToolbarBinding
import com.lava.cricx.ui.base.BaseActivity
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
        setupAppBar()
    }

    private fun setupAppBar() {
        toolbarBinding.ivBackArrow.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
        toolbarBinding.tvToolbarTitle.text = getString(R.string.home)
        toolbarBinding.ivNavDrawer.setOnClickListener {
            binding.drawerLayout.openDrawer(GravityCompat.END, true)
        }
    }

    private fun setupSmoothBottomMenu() {
        val popupMenu = PopupMenu(this, null)
        popupMenu.inflate(R.menu.menu_bottom_navbar)
        val menu = popupMenu.menu
        binding.bottomBar.setupWithNavController(menu, navController)
        binding.bottomBar.onItemSelected = { position ->
            toolbarBinding.tvToolbarTitle.text = when (position) {
                0 -> getString(R.string.home)
                1 -> getString(R.string.news)
                2 -> getString(R.string.matches)
                3 -> getString(R.string.profile)
                else -> ""
            }
        }
    }

    override fun observeViewModel() {}
}