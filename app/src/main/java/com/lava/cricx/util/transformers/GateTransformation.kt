package com.lava.cricx.util.transformers

import android.view.View
import androidx.viewpager2.widget.ViewPager2


class GateTransformation : ViewPager2.PageTransformer {
    override fun transformPage(page: View, position: Float) {
        page.translationX = -position * page.width
        if (position < -1) {    // [-Infinity,-1)
            // This page is way off-screen to the left.
            page.alpha = 0f
        } else if (position <= 0) {    // [-1,0]
            page.alpha = 1f
            page.pivotX = 0f
            page.rotationY = 90 * Math.abs(position)
        } else if (position <= 1) {    // (0,1]
            page.alpha = 1f
            page.pivotX = page.width.toFloat()
            page.rotationY = -90 * Math.abs(position)
        } else {    // (1,+Infinity]
            // This page is way off-screen to the right.
            page.alpha = 0f
        }
    }
}