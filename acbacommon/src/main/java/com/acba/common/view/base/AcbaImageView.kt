package com.acba.common.view.base

import android.content.Context
import android.os.SystemClock
import android.util.AttributeSet
import android.view.View
import com.acba.common.R

class AcbaImageView : androidx.appcompat.widget.AppCompatImageView {

    private var lastClickTime: Long = 0
    private var isPreventDoubleClick: Boolean = true
    private var realClickListener: OnClickListener? = null

    constructor(context: Context) : super(context) {
        init(context)
    }

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet) {
        init(context, attributeSet)
    }

    constructor(context: Context, attributeSet: AttributeSet, defStyledAttr: Int) : super(context, attributeSet) {
        init(context, attributeSet)
    }

    private fun init(context: Context, attributeSet: AttributeSet? = null) {
        val typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.AcbaImageView)
        try {
            isPreventDoubleClick =
                    typedArray.getBoolean(R.styleable.AcbaImageView_isPreventDoubleClick, false)
        } finally {
            typedArray.recycle()
        }
    }

    override fun setOnClickListener(listener: OnClickListener?) {
        if (isPreventDoubleClick) {
            realClickListener = listener
            super.setOnClickListener(object : CustomClickListener() {})
        } else {
            super.setOnClickListener(listener)
        }


    }

    abstract inner class CustomClickListener : OnClickListener {
        override fun onClick(v: View?) {
            if (SystemClock.elapsedRealtime() - lastClickTime < 1000) {
                return
            }
            lastClickTime = SystemClock.elapsedRealtime()
            realClickListener?.onClick(v)
        }
    }
}