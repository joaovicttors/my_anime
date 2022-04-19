package com.joaovicttors.domain.entities

sealed class Response<T> {
    data class Error<T>(val message: String?) : Response<T>()
    data class Success<T>(val body: T) : Response<T>()
}