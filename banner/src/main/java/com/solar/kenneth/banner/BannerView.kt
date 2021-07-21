package com.solar.kenneth.banner

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.*
import androidx.viewpager2.widget.ViewPager2
import com.solar.kenneth.banner.ScrollHandler.Companion.HANDLER_MESSAGE_ID_SLIDE_NEXT

class BannerView @JvmOverloads constructor(
  context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr), LifecycleObserver {

  private val ratioWidth: Int
  private val ratioHeight: Int
  private val infinity: Boolean

  private val bannerPagerCount: TextView by lazy { findViewById(R.id.banner_pager_count) }
  private val bannerPager: ViewPager2 by lazy { findViewById(R.id.banner_pager) }

  private val scrollHandler = ScrollHandler(::slideNext)

  private var autoScroll: Boolean = true
  private var bannerList: List<Banner> = listOf()

  private val bannerViewListeners : ArrayList<BannerViewListener> by lazy { arrayListOf() }

  init {
    context.theme.obtainStyledAttributes(
      attrs,
      R.styleable.BannerView,
      0, 0
    ).apply {
      try {
        autoScroll = getBoolean(R.styleable.BannerView_autoScroll, DEFAULT_AUTO_START)
        infinity = getBoolean(R.styleable.BannerView_infinity, DEFAULT_INFINITY)
        ratioWidth = getInt(R.styleable.BannerView_ratio_width, DEFAULT_BANNER_WIDTH)
        ratioHeight = getInt(R.styleable.BannerView_ratio_height, DEFAULT_BANNER_HEIGHT)
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
  }

  fun addBannerViewListener(bannerViewListener: BannerViewListener) {
    bannerViewListeners.add(bannerViewListener)
  }

  fun setBannerList(bannerList: List<Banner>) {
    this.bannerList = bannerList
    bannerPager.adapter = BannerViewPageAdapter(bannerList) { iv, banner ->
      bannerViewListeners.forEach {
        it.onBannerBinding(iv, banner)
      }
    }
    bannerPagerCount.text = context.getString(R.string.pager_count, 1, bannerList.count())
  }

  override fun onAttachedToWindow() {
    super.onAttachedToWindow()
    findViewTreeLifecycleOwner()?.lifecycle?.addObserver(this)
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

    if (autoScroll) {
      startAutoSlide()
    }
  }

  @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
  fun startAutoSlide() {
    scrollHandler.removeMessages(HANDLER_MESSAGE_ID_SLIDE_NEXT)
    scrollHandler.sendEmptyMessageDelayed(HANDLER_MESSAGE_ID_SLIDE_NEXT, DEFAULT_SLIDE_TIME)
  }

  @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
  fun stopAutoSlide() {
    scrollHandler.removeMessages(HANDLER_MESSAGE_ID_SLIDE_NEXT)
  }

  companion object {
    private const val DEFAULT_SLIDE_TIME = 2000L

    private const val DEFAULT_BANNER_WIDTH = 3
    private const val DEFAULT_BANNER_HEIGHT = 2
    private const val DEFAULT_INFINITY = false
    private const val DEFAULT_AUTO_START = true
  }
}