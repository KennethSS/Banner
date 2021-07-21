package com.solar.kenneth.banner

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class BannerPageViewHolder(
  view: View,
  private val onPageChanged: (iv: ImageView, banner: Banner) -> Unit
) : ViewHolder(view) {

  private val bannerImage: ImageView by lazy {
    view.findViewById(R.id.banner_pager_image)
  }

  fun bind(banner: Banner) {
    onPageChanged(bannerImage, banner)
  }
}