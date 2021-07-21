package com.solar.kenneth.banner

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class BannerViewHolder(view: View) : RecyclerView.ViewHolder(view) {

  private val bannerView: BannerView = view.findViewById(R.id.banner_view)

  init {
    bannerView.addBannerViewListener(object: BannerViewListener {
      override fun onBannerBinding(iv: ImageView, banner: Banner) {
        println("onBannerBinding: $banner")
        Glide.with(iv)
          .load(banner.image)
          .apply(RequestOptions().centerCrop())
          .into(iv)
      }
    })

    bannerView.setBannerList(BannerMock.getMockBanner())
  }

  fun bind() {
    bannerView.startAutoSlide()
  }

  fun recycled() {
    bannerView.stopAutoSlide()
  }
}