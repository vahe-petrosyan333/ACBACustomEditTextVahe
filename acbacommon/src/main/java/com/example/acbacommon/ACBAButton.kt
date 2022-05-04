package com.example.acbacommon

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.AttributeSet


class ACBAButton : androidx.appcompat.widget.AppCompatButton {

    var isBlockDoubleClick = false
    var isClicked = false
    private var clickInterval = 0

    constructor(context: Context) : super(context)

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet) {
        init(context, attributeSet)
    }

    constructor(context: Context, attributeSet: AttributeSet, defStyledAttr: Int)
            : super(context, attributeSet, defStyledAttr) {
        init(context, attributeSet)
    }

    private fun init(context: Context, attributeSet: AttributeSet) {
        val incoming = context.obtainStyledAttributes(attributeSet, R.styleable.ACBAButton)
        isBlockDoubleClick = incoming.getBoolean(R.styleable.ACBAButton_isBlockDoubleClick, false)
        clickInterval = incoming.getInt(R.styleable.ACBAButton_clickInterval, 800)
        incoming.recycle()
    }

    override fun setOnClickListener(listener: OnClickListener?) {
        super.setOnClickListener() {
            if (!isBlockDoubleClick) listener?.onClick(it)
            else if (!isClicked) {
                listener?.onClick(it)
                isClicked = true
                Handler(Looper.getMainLooper()).postDelayed({
                    isClicked = false
                }, clickInterval.toLong())
            }
        }
    }
}