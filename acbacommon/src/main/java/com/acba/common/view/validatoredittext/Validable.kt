package com.acba.common.view.validatoredittext

interface Validable {
    fun validate()
    fun showDefaultState()
    fun showErrorState(messageRes: Int)
}