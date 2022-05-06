package com.acba.acbadigital.mapper

interface Mapper<T, K> {
    fun map(model: T): K
}