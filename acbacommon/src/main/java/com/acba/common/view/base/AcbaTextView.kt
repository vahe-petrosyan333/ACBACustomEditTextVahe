package com.acba.common.view.base

import android.content.Context
import android.util.AttributeSet
import com.acba.common.R
import com.acba.common.util.listeners.CustomClickListener
import com.google.android.material.textview.MaterialTextView

class AcbaTextView : MaterialTextView {
    private var isPreventDoubleClick: Boolean = true

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
        val typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.AcbaTextView)
        try {
            isPreventDoubleClick =
                    typedArray.getBoolean(R.styleable.AcbaButton_isPreventDoubleClick, false)
        } finally {
            typedArray.recycle()
        }
    }

    override fun setOnClickListener(listener: OnClickListener?) {
        if (isPreventDoubleClick) super.setOnClickListener(CustomClickListener(listener))
        else super.setOnClickListener(listener)
    }
}