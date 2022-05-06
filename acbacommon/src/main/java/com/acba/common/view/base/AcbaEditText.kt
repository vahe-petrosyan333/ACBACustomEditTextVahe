package com.acba.common.view.base

import android.content.Context
import android.util.AttributeSet
import android.view.View
import com.acba.common.R
import com.acba.common.view.validatoredittext.Validator
import com.acba.common.view.validatoredittext.ValidatorListener
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout


class AcbaEditText : TextInputEditText, ValidatorListener {

    val EMAIL_ERROR by lazy { "Incorrect email" }
    val PASSWORD_ERROR by lazy { "Incorrect password" }

    lateinit var validator: Validator

    private var mType: Int = 0
    var layoutId: Int = 0
    var textInputLayout: TextInputLayout? = null
    var errorMessage: String? = ""

    constructor(context: Context) : super(context) {
        init(context)
    }

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

    private fun init(context: Context, attributeSet: AttributeSet? = null) {
        validator = Validator()
        val typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.ACBAEditText)
        mType = typedArray.getInteger(R.styleable.ACBAEditText_types, 0)
        layoutId = typedArray.getResourceId(R.styleable.ACBAEditText_layoutId, 0)
        errorMessage = typedArray.getString(R.styleable.ACBAEditText_error)
        typedArray.recycle()
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        textInputLayout = (parent.parent as View).findViewById(layoutId)
    }

    override fun validate() {

        when (mType) {
            1 -> {
                if (!validator.isValidEmail(text.toString())) {
                    showErrorWhenIsNotValid(errorMessage ?: EMAIL_ERROR)
                } else textInputLayout?.error = null
            }
            2 -> {
                if (!validator.isValidPassword(text.toString())) {
                    showErrorWhenIsNotValid(errorMessage ?: PASSWORD_ERROR)
                } else textInputLayout?.error = null

            }

        }
    }

    fun showErrorWhenIsNotValid(errorMessage: String) {
        textInputLayout?.error = errorMessage
    }
}