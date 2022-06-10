package com.example.common.view.validatoredittext

interface Validable {
    fun validate(): Boolean
    fun showDefaultState()
    fun showErrorState(messageText: String)
}