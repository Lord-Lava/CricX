package com.lava.cricx.ui.component.players.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.lava.cricx.ui.component.players.infoFragments.BowlingFragment
import com.lava.cricx.ui.component.players.infoFragments.BattingFragment
import com.lava.cricx.ui.component.players.infoFragments.CareerFragment
import com.lava.cricx.ui.component.players.infoFragments.InfoFragment

class ViewPagerAdapter(fragmentActivity: FragmentActivity, private val titles: Array<String>) :
    FragmentStateAdapter(fragmentActivity) {
    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> return InfoFragment()
            1 -> return BattingFragment()
            2 -> return BowlingFragment()
            3 -> return CareerFragment()
        }
        return InfoFragment()
    }

    override fun getItemCount(): Int {
        return titles.size
    }
}