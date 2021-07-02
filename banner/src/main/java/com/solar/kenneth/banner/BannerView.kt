package com.solar.kenneth.banner

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.viewpager2.widget.ViewPager2

class BannerView @JvmOverloads constructor(
  context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

  private val ratioWidth: Int
  private val ratioHeight: Int
  private val infinity: Boolean

  private val bannerPagerCount: TextView by lazy { findViewById(R.id.banner_pager_count) }
  private val bannerPager: ViewPager2 by lazy { findViewById(R.id.banner_pager) }

  private val scrollHandler = ScrollHandler(::slideNext, ::startAutoSlide)

  private var autoScroll: Boolean = true
  private var bannerList: List<Banner> = listOf()

  private var bannerViewListener: BannerViewListener? = null

  init {
    context.theme.obtainStyledAttributes(
      attrs,
      R.styleable.BannerView,
      0, 0
    ).apply {
      try {
        autoScroll = getBoolean(R.styleable.BannerView_autoScroll, true)
        infinity = getBoolean(R.styleable.BannerView_infinity, false)
        ratioWidth = getInt(R.styleable.BannerView_ratio_width, 3)
        ratioHeight = getInt(R.styleable.BannerView_ratio_height, 2)
      } finally {
        recycle()
      }
    }

    LayoutInflater.from(context).inflate(R.layout.view_banner, this, true)

    with(bannerPager) {
      (layoutParams as LayoutParams).dimensionRatio = "$ratioWidth:$ratioHeight"
      bannerPager.registerOnPageChangeCallback(
        BannerViewPageCallback(
          bannerPagerCount = bannerPagerCount,
          totalCount = { bannerList.count() },
          isDragging = { isDragging: Boolean ->
            if (isDragging) stopAutoSlide()
            else startAutoSlide()
          })
      )
    }

    startAutoSlide()
  }

  fun setBannerViewListener(bannerViewListener: BannerViewListener) {
    this.bannerViewListener = bannerViewListener
  }

  fun setBannerList(bannerList: List<Banner>) {
    this.bannerList = bannerList
    bannerPager.adapter = BannerViewPageAdapter(bannerList, this.bannerViewListener)
    bannerPagerCount.text = context.getString(R.string.pager_count, 1, bannerList.count())
  }

  private fun slideNext() {
    val itemCount = bannerPager.adapter?.itemCount ?: 0
    val nextIndex = bannerPager.currentItem + 1

    if (nextIndex < itemCount) {
      bannerPager.setCurrentItem(nextIndex, true)
    } else {
      if (infinity) bannerPager.setCurrentItem(0, false)
      else stopAutoSlide()
    }
  }

  private fun startAutoSlide() {
    if (autoScroll) {
      scrollHandler.removeMessages(HANDLER_MESSAGE_ID)
      scrollHandler.sendEmptyMessageDelayed(HANDLER_MESSAGE_ID, 2000L)
    }
  }

  private fun stopAutoSlide() {
    scrollHandler.removeMessages(HANDLER_MESSAGE_ID)
  }

  companion object {
    private const val HANDLER_MESSAGE_ID = 0
  }
}