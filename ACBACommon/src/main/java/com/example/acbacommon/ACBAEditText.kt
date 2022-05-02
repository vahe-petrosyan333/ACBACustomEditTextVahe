package com.example.acbacommon

import android.content.Context
import android.util.AttributeSet
import android.util.TypedValue
import androidx.appcompat.widget.AppCompatEditText


class ACBAEditText : AppCompatEditText, ValidatorListener {
    private var mType: Int = 0
     var layoutId: Int = 0

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
        val incoming = context.obtainStyledAttributes(attributeSet, R.styleable.ACBAEditText)
        mType = incoming.getInteger(R.styleable.ACBAEditText_types, 0)
        layoutId = incoming.getResourceId(R.styleable.ACBAEditText_layoutId,0)
        incoming.recycle()
    }

    override fun validate(): Boolean {
        return when (mType) {
            1 -> {
                Validator.isValidEmail(text.toString())
            }
            2 -> {
                Validator.isValidPassword(text.toString())
            }
            else -> false
        }
    }

}