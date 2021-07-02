package com.solar.kenneth.banner

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class BannerViewPageAdapter(
  private val banner: List<Banner>,
  private val listener: BannerViewListener?
) : RecyclerView.Adapter<BannerPageViewHolder>() {
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerPageViewHolder {
    val inflater = LayoutInflater.from(parent.context)
    val view = inflater.inflate(R.layout.banner_page_item, parent, false)
    return BannerPageViewHolder(view, listener)
  }

  override fun onBindViewHolder(holder: BannerPageViewHolder, position: Int) {
    holder.bind(banner[position])
  }

  override fun getItemCount(): Int = banner.count()
}