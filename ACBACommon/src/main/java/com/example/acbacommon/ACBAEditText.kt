package com.example.acbacommon

import android.content.Context
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import androidx.appcompat.widget.AppCompatEditText
import com.google.android.material.textfield.TextInputLayout


class ACBAEditText : AppCompatEditText, ValidatorListener {

    val EMAIL_ERROR = "Incorrect email"
    val PASSWORD_ERROR = "Incorrect password"

    private var mType: Int = 0
    var layoutId: Int = 0
    var textInputLayout: TextInputLayout? = null


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
        layoutId = incoming.getResourceId(R.styleable.ACBAEditText_layoutId, 0)

        incoming.recycle()
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        textInputLayout = (parent.parent as View).findViewById(layoutId)
    }

    override fun validate() {

        when (mType) {
            1 -> {
                if (!Validator.isValidEmail(text.toString())) {
                    showErrorWhenIsNotValid(EMAIL_ERROR)
                }
            }
            2 -> {
                if (!Validator.isValidPassword(text.toString())) {
                    showErrorWhenIsNotValid(PASSWORD_ERROR)
                }

            }

        }
    }

    fun showErrorWhenIsNotValid(errorMessage: String) {
        textInputLayout?.error = errorMessage
    }
}