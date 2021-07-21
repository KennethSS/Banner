package com.solar.kenneth.banner

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class BannerViewPageAdapter(
  private val banner: List<Banner>,
  private val onPageChanged: (iv: ImageView, banner: Banner) -> Unit = { _, _ -> }
) : RecyclerView.Adapter<BannerPageViewHolder>() {
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerPageViewHolder {
    val inflater = LayoutInflater.from(parent.context)
    val view = inflater.inflate(R.layout.banner_page_item, parent, false)
    return BannerPageViewHolder(view, onPageChanged)
  }

  override fun onBindViewHolder(holder: BannerPageViewHolder, position: Int) {
    holder.bind(banner[position])
  }

  override fun getItemCount(): Int = banner.count()
}