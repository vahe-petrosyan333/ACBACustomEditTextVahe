package com.acba.common.view.base

import android.content.Context
import android.util.AttributeSet
import android.view.View
import com.acba.common.R
import com.acba.common.util.extentions.emailValidator
import com.acba.common.util.extentions.passwordValidator
import com.acba.common.util.extentions.regexValidator
import com.acba.common.util.extentions.requiredValidator
import com.acba.common.view.validatoredittext.Validable
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout


class AcbaEditText : TextInputEditText, Validable {

    companion object {
        const val UNDEFINED = -1
    }

    private var isRequired: Boolean = false
    private var layoutId: Int = UNDEFINED
    private var textInputLayout: TextInputLayout? = null
    private var errorText: String? = ""
    private var mMinLengthErrorMessage: String? = ""
    private var regex: String? = ""
    private var validationTag: Int = UNDEFINED
    private var minLength: Int = UNDEFINED


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
        val typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.AcbaEditText)
        try {
            layoutId = typedArray.getResourceId(R.styleable.AcbaEditText_layoutId, 0)
            errorText = typedArray.getString(R.styleable.AcbaEditText_errorMessage)
            regex = typedArray.getString(R.styleable.AcbaEditText_regex)
            validationTag = typedArray.getInt(R.styleable.AcbaEditText_validator, 0)
            minLength = typedArray.getInt(R.styleable.AcbaEditText_minLength, 0)
            isRequired = typedArray.getBoolean(R.styleable.AcbaEditText_isRequired, false)
        } finally {
            typedArray.recycle()
        }

        try {
            mMinLengthErrorMessage = String.format(context.getString(context.resources.getIdentifier("min_length_message", "string", context.packageName)), minLength)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        textInputLayout = (parent.parent as View).findViewById(layoutId)
    }

    override fun validate(): Boolean {
        val target = text.toString()
        if (validationTag != UNDEFINED && !identifyValidationPatternAndValidate(validationTag, target)) {
            if (isRequired || target.isNotEmpty()) {
                (errorText ?: getValidationText(validationTag))?.let { showErrorState(it) }
                return false
            }
        }
        showDefaultState()
        return true
    }

    private fun identifyValidationPatternAndValidate(validationTag: Int, target: String): Boolean {
        return when (validationTag) {
            R.integer.validate_email -> target.emailValidator()
            R.integer.validate_password -> target.passwordValidator()
            R.integer.validate_required_field -> target.requiredValidator()
            R.integer.validate_regex -> target.regexValidator(regex?.toRegex())
            else -> false
        }
    }

    private fun getValidationText(validationTag: Int): String? {
        return when (validationTag) {
            R.integer.validate_email ->
                context.getString(context.resources.getIdentifier("email_validation_error", "string", context.packageName))
            R.integer.validate_password ->
                context.getString(context.resources.getIdentifier("password_validation_error", "string", context.packageName))
            R.integer.validate_required_field ->
                context.getString(context.resources.getIdentifier("empty_message", "string", context.packageName))
            else -> ""
        }
    }

    override fun showDefaultState() {
        TODO("Not yet implemented")
    }

    override fun showErrorState(errorText: String) {
        textInputLayout?.error = errorText
    }
}