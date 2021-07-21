package com.solar.kenneth.banner

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    //val bannerView = findViewById<BannerView>(R.id.banner_view)

    val listView = findViewById<RecyclerView>(R.id.main_list_view).apply {
      adapter = MainListAdapter().apply {
        notifyDataSetChanged()
      }
    }


    (listView.layoutManager as LinearLayoutManager)


    /*Handler().postDelayed({
      bannerView.setBannerList(BannerMock.getMockBanner())
    }, 3000L)

    bannerView.addBannerViewListener(object: BannerViewListener {
      override fun onBannerBinding(iv: ImageView, banner: Banner) {
        Glide.with(iv)
          .load(banner.image)
          .into(iv)
      }
    })*/
  }
}