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

    val EMAIL_DEFAULT_ERROR by lazy { "Incorrect email" }
    val PASSWORD_DEFAULT_ERROR by lazy { "Incorrect password" }

    lateinit var validator: Validator

    private var layoutId: Int = 0
    private var textInputLayout: TextInputLayout? = null
    private var errorMessage: String? = ""
    private var mEmptyErrorMessage: String? = ""
    private var mMinLengthErrorMessage: String? = ""
    private var mEmailErrorMessage: String? = ""
    private var mPasswordErrorMessage: String? = ""
    private var regex: String? = ""
    private var validatorType: Int = 0
    private var minLength: Int = 0


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
        try {
            layoutId = typedArray.getResourceId(R.styleable.ACBAEditText_layoutId, 0)
            errorMessage = typedArray.getString(R.styleable.ACBAEditText_errorMessage)
            regex = typedArray.getString(R.styleable.ACBAEditText_regex)
            validatorType = typedArray.getInt(R.styleable.ACBAEditText_validator, 0)
            minLength = typedArray.getInt(R.styleable.ACBAEditText_minLength, 0)
        } finally {
            typedArray.recycle()
        }

        try {
            mEmptyErrorMessage = context.getString(context.resources.getIdentifier("empty_message", "string", context.packageName))
            mEmailErrorMessage = context.getString(context.resources.getIdentifier("email_validation_error", "string", context.packageName))
            mPasswordErrorMessage = context.getString(context.resources.getIdentifier("password_validation_error", "string", context.packageName))
            mMinLengthErrorMessage = String.format(context.getString(context.resources.getIdentifier("min_length_message", "string", context.packageName)), minLength)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        textInputLayout = (parent.parent as View).findViewById(layoutId)
    }

    override fun validate() {
        if (text.isNullOrEmpty()) {
            showErrorWhenIsNotValid(mEmptyErrorMessage ?: "")
            return
        }
        if (!validator.checkMinLength(text.toString(), minLength)) {
            showErrorWhenIsNotValid(mMinLengthErrorMessage ?: "")
            return
        }
        when (validatorType) {
            1 -> {
                if (!validator.isValidEmail(text.toString())) {
                    showErrorWhenIsNotValid(mEmailErrorMessage ?: EMAIL_DEFAULT_ERROR)
                } else textInputLayout?.error = null
            }
            2 -> {
                if (!validator.isValidPassword(text.toString())) {
                    showErrorWhenIsNotValid(mPasswordErrorMessage ?: PASSWORD_DEFAULT_ERROR)
                } else textInputLayout?.error = null

            }
            3 -> {
                if (!validator.validateFromRegex(regex, text.toString())) {
                    showErrorWhenIsNotValid(errorMessage ?: "")
                }
            }

        }
    }

    fun showErrorWhenIsNotValid(errorMessage: String) {
        textInputLayout?.error = errorMessage
    }
}