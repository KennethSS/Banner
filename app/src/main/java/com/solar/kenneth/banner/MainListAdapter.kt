package com.solar.kenneth.banner

import android.os.Parcelable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class MainListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

  private var savedInstance: Parcelable? = null

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
    val inflater = LayoutInflater.from(parent.context)
    if (viewType == R.layout.item_banner) {
      println("onCreateViewHolder Banner")
      return BannerViewHolder(inflater.inflate(R.layout.item_banner, parent, false))
    }
    else {
      println("onCreateViewHolder TextVIew")
      return TextViewHolder(inflater.inflate(viewType, parent, false))
    }
  }

  override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
    when(holder) {
      is BannerViewHolder -> {
        println("BannerViewHolder Bind")
        holder.bind()
      }
      is TextViewHolder -> { }
    }
  }

  override fun getItemCount(): Int = 30
  override fun getItemViewType(position: Int): Int {
    return if (position ==0) R.layout.item_banner
    else R.layout.item_normal
  }

  override fun onViewRecycled(holder: RecyclerView.ViewHolder) {
    if (holder is BannerViewHolder) {
      holder.recycled()
    }
    super.onViewRecycled(holder)
  }
}