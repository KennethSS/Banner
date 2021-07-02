package com.solar.kenneth.banner

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.ImageView
import com.bumptech.glide.Glide

class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    val bannerView = findViewById<BannerView>(R.id.banner_view)

    Handler().postDelayed({
      bannerView.setBannerList(BannerMock.getMockBanner())
    }, 3000L)

    bannerView.setBannerViewListener(object: BannerViewListener {
      override fun onBannerBinding(iv: ImageView, banner: Banner) {
        Glide.with(iv)
          .load(banner.image)
          .into(iv)
      }
    })
  }
}