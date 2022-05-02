package com.example.common

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText


class ACBAEditText : AppCompatEditText {

    constructor(context: Context) : super(context)
    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet) {
        init(context, attributeSet)
    }

    constructor(context: Context, attributeSet: AttributeSet, defStyledAttr: Int) : super(
        context,
        attributeSet,
        defStyledAttr
    ) {
        init(context, attributeSet)
    }

    private fun init(context: Context, attributeSet: AttributeSet) {

    }
}