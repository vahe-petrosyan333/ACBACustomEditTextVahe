package com.example.common.view.base

import android.content.Context
import android.util.AttributeSet
import com.example.common.R
import com.example.common.util.listeners.CustomClickListener
import com.google.android.material.button.MaterialButton


class CustomButton : MaterialButton {

    private var isPreventDoubleClick: Boolean = true

    constructor(context: Context) : super(context) {
        init(context)
    }

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet) {
        init(context, attributeSet)
    }

    constructor(context: Context, attributeSet: AttributeSet, defStyledAttr: Int)
            : super(context, attributeSet, defStyledAttr) {
        init(context, attributeSet)
    }

    private fun init(context: Context, attributeSet: AttributeSet? = null) {
        val typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.CustomButton)
        try {
            isPreventDoubleClick =
                typedArray.getBoolean(R.styleable.CustomButton_isPreventDoubleClick, false)
        } finally {
            typedArray.recycle()
        }
    }

    override fun setOnClickListener(listener: OnClickListener?) {
        if (isPreventDoubleClick) super.setOnClickListener(CustomClickListener(listener))
        else super.setOnClickListener(listener)
    }
}