package com.solar.kenneth.banner

import android.widget.ImageView

interface BannerViewListener {
  fun onBannerBinding(iv: ImageView, banner: Banner)
}