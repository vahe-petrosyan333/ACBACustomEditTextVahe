package com.example.common.view.base

import android.content.Context
import android.util.AttributeSet
import com.example.common.R
import com.google.android.material.appbar.MaterialToolbar

class CustomToolBar : MaterialToolbar {
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
        val typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.CustomToolBar)
        typedArray.recycle()
    }

}