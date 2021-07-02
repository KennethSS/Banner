package com.solar.kenneth.banner

import android.widget.TextView
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import androidx.viewpager2.widget.ViewPager2.SCROLL_STATE_DRAGGING
import androidx.viewpager2.widget.ViewPager2.SCROLL_STATE_IDLE

class BannerViewPageCallback(
  private val bannerPagerCount: TextView,
  private val totalCount: () -> Int,
  private val isDragging: (isDragging: Boolean) -> Unit) : OnPageChangeCallback() {

  override fun onPageSelected(position: Int) {
    super.onPageSelected(position)
    bannerPagerCount.text = bannerPagerCount.context.getString(R.string.pager_count, position.plus(1), totalCount())
  }

  override fun onPageScrollStateChanged(state: Int) {
    super.onPageScrollStateChanged(state)
    when(state) {
      SCROLL_STATE_IDLE -> isDragging(false)
      SCROLL_STATE_DRAGGING -> isDragging(true)
    }
  }
}