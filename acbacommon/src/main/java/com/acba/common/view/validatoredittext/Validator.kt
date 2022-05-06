package com.acba.common.view.validatoredittext

import java.util.regex.Pattern

class Validator {

    private val validEmailAddressRegex =
        Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE)

    private val validPasswordRegex =
        Pattern.compile(
            ".{8,20}",
            Pattern.CASE_INSENSITIVE
        )

    fun isValidEmail(text: String): Boolean {
        return validEmailAddressRegex.matcher(text).find()
    }

    fun isValidPassword(text: String): Boolean {
        return validPasswordRegex.matcher(text).find()
    }
}