package com.solar.kenneth.banner

import android.os.Handler
import android.os.Looper
import android.os.Message

class ScrollHandler(
  private val slideNext: () -> Unit,
  private val startAutoSlide:() -> Unit
) : Handler(Looper.getMainLooper()) {
  override fun handleMessage(msg: Message) {
    super.handleMessage(msg)

    if (msg.what == 0) {
      slideNext()
      startAutoSlide()
    }
  }
}