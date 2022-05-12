package com.acba.common.view.validatoredittext

import android.content.Context
import android.util.AttributeSet
import android.view.View
import com.acba.common.R
import com.acba.common.util.extentions.*
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout


class AcbaEditText : TextInputEditText, Validable {

    companion object {
        const val UNDEFINED = -1
    }

    private var isRequired: Boolean = false
    private var layoutId: Int = UNDEFINED
    private var textInputLayout: TextInputLayout? = null
    private var errorText: String? = null
    private var mMinLengthErrorMessage: String? = null
    private var regex: String? = null
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
            errorText = typedArray.getString(R.styleable.AcbaEditText_errorText)
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
            resources.getInteger(R.integer.validate_email) -> target.emailValidator()
            resources.getInteger(R.integer.validate_password) -> target.passwordValidator()
            resources.getInteger(R.integer.validate_required_field) -> target.requiredValidator()
            resources.getInteger(R.integer.validate_regex) -> target.regexValidator(regex?.toRegex())
            resources.getInteger(R.integer.validate_min_length) -> target.minLengthValidator(minLength)
            resources.getInteger(R.integer.validate_latin_character) -> target.latinCharacterValidator()
            else -> false
        }
    }

    private fun getValidationText(validationTag: Int): String? {
        return when (validationTag) {
            resources.getInteger(R.integer.validate_email) ->
                context.getString(context.resources.getIdentifier("email_validation_error", "string", context.packageName))
            resources.getInteger(R.integer.validate_password) ->
                context.getString(context.resources.getIdentifier("password_validation_error", "string", context.packageName))
            resources.getInteger(R.integer.validate_required_field) ->
                context.getString(context.resources.getIdentifier("empty_message", "string", context.packageName))
            resources.getInteger(R.integer.validate_min_length) ->
                context.getString(context.resources.getIdentifier("min_length_message", "string", context.packageName))
            resources.getInteger(R.integer.validate_latin_character) ->
                context.getString(context.resources.getIdentifier("latin_character_error", "string", context.packageName))
            else -> ""
        }
    }

    override fun showDefaultState() {
        textInputLayout?.error = null
        textInputLayout?.isErrorEnabled = false
    }

    override fun showErrorState(errorText: String) {
        textInputLayout?.isErrorEnabled = true
        textInputLayout?.error = errorText
    }
}