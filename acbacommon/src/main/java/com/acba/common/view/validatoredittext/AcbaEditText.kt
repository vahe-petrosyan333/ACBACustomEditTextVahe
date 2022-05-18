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
    private var errorTextFromRegex: String? = null
    private var validateWithRegex: String? = null
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
        attributeSet, defStyledAttr
    ) {
        init(context, attributeSet)
    }

    private fun init(context: Context, attributeSet: AttributeSet? = null) {
        val typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.AcbaEditText)
        try {
            layoutId = typedArray.getResourceId(R.styleable.AcbaEditText_layoutId, 0)
            errorTextFromRegex = typedArray.getString(R.styleable.AcbaEditText_regexErrorText)
            validateWithRegex = typedArray.getString(R.styleable.AcbaEditText_validateWithRegex)
            validationTag = typedArray.getInt(R.styleable.AcbaEditText_validatorType, -1)
            minLength = typedArray.getInt(R.styleable.AcbaEditText_minLength, 0)
            isRequired = typedArray.getBoolean(R.styleable.AcbaEditText_isRequired, false)
        } finally {
            typedArray.recycle()
        }
        onFocusChangeListener = null
    }

    override fun setOnFocusChangeListener(l: OnFocusChangeListener?) {
        super.setOnFocusChangeListener { view, onFocus ->
            if (!onFocus) {
                validate()
            }
            l?.onFocusChange(view, onFocus)
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
                showErrorState(getValidationText(validationTag))
                return false
            }
        }
        if (!validateWithRegex.isNullOrEmpty()) {
            if (!target.regexValidator(validateWithRegex?.toRegex())) {
                showErrorState(errorTextFromRegex ?: "")
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
            resources.getInteger(R.integer.validate_min_length) -> target.minLengthValidator(minLength)
            resources.getInteger(R.integer.validate_latin_character) -> target.latinCharacterValidator()
            else -> false
        }
    }

    private fun getValidationText(validationTag: Int): String {
        return when (validationTag) {
            resources.getInteger(R.integer.validate_email) -> context.getString(R.string.email_validation_error)
            resources.getInteger(R.integer.validate_password) -> context.getString(R.string.password_validation_error)
            resources.getInteger(R.integer.validate_required_field) -> context.getString(R.string.empty_error_text)
            resources.getInteger(R.integer.validate_min_length) -> String.format(context.getString(R.string.min_length_error_text), minLength)
            resources.getInteger(R.integer.validate_latin_character) -> context.getString(R.string.latin_character_error)
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