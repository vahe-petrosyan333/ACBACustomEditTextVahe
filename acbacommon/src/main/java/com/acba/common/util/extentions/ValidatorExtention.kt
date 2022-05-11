package com.acba.common.util.extentions

import android.util.Patterns
import java.nio.charset.Charset
import java.util.regex.Matcher
import java.util.regex.Pattern


    fun String.requiredValidator(): Boolean = this.isNotEmpty()
    fun String.emailValidator(): Boolean = Patterns.EMAIL_ADDRESS.matcher(this).matches()
    fun String.passwordValidator(): Boolean = isValidPassword(this)
    fun String.latinCharacterValidator(): Boolean = Charset.forName("US-ASCII").newEncoder().canEncode(this)
    fun String.regexValidator(regex: Regex?): Boolean = regex?.let(this::matches) ?: true

    private fun isValidPassword(password: String?): Boolean {
        val passwordRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{4,}$"
        val pattern: Pattern = Pattern.compile(passwordRegex)
        val matcher: Matcher = pattern.matcher(password?:"")
        return matcher.matches()
    }
