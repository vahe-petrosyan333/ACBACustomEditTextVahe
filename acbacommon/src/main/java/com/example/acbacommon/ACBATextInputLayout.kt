package com.example.acbacommon

import android.content.Context
import android.util.AttributeSet
import com.google.android.material.textfield.TextInputLayout

class ACBATextInputLayout : TextInputLayout {

    constructor(context: Context) : super(context)
    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet) {
        init(context, attributeSet)
    }

    constructor(context: Context, attributeSet: AttributeSet, defStyledAttr: Int) :
            super(context, attributeSet, defStyledAttr) {
        init(context, attributeSet)
    }

    private fun init(context: Context, attributeSet: AttributeSet) {
        val incomingValues = context.obtainStyledAttributes(
            attributeSet,
            R.styleable.ACBAETextInput
        )
    }
}