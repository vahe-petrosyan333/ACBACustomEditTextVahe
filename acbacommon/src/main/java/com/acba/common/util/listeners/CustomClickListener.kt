package com.acba.common.util.listeners

import android.os.SystemClock
import android.view.View

class CustomClickListener(private val listener: View.OnClickListener?) : View.OnClickListener {
    private var lastClickTime: Long = 0
    override fun onClick(v: View?) {
        if (SystemClock.elapsedRealtime() - lastClickTime < 1000) {
            return
        }
        lastClickTime = SystemClock.elapsedRealtime()
        listener?.onClick(v)
    }
}