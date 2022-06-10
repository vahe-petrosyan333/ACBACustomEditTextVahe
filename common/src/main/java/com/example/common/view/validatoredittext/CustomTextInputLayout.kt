package com.example.common.view.validatoredittext

import android.content.Context
import android.util.AttributeSet
import com.example.common.R
import com.google.android.material.textfield.TextInputLayout

class CustomTextInputLayout : TextInputLayout {

    constructor(context: Context) : super(context)
    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet) {
        init(context, attributeSet)
    }

    constructor(context: Context, attributeSet: AttributeSet, defStyledAttr: Int) :
            super(context, attributeSet, defStyledAttr) {
        init(context, attributeSet)
    }

    private fun init(context: Context, attributeSet: AttributeSet) {
        val typedArray = context.obtainStyledAttributes(
            attributeSet,
            R.styleable.CustomTextInputLayout
        )
        typedArray.recycle()
    }
}