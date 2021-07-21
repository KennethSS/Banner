package com.solar.kenneth.banner

import android.os.Handler
import android.os.Looper
import android.os.Message

class ScrollHandler(
  private val slideNext: () -> Unit
) : Handler(Looper.getMainLooper()) {

  override fun handleMessage(msg: Message) {
    super.handleMessage(msg)

    when (msg.what) {
      HANDLER_MESSAGE_ID_SLIDE_NEXT -> {
        slideNext()
      }
    }
  }

  companion object {
    const val HANDLER_MESSAGE_ID_SLIDE_NEXT = 0
  }
}